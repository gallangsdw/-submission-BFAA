package com.sdwtech.githubuser.detail

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.sdwtech.githubuser.R
import com.sdwtech.githubuser.adapter.SectionsPagerAdapter
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.databinding.ActivityDetailBinding
import com.sdwtech.githubuser.db.UserContract
import com.sdwtech.githubuser.db.UserContract.UserColumn.Companion.CONTENT_URI
import com.sdwtech.githubuser.db.UserHelper
import com.sdwtech.githubuser.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
    private lateinit var binding:ActivityDetailBinding
    private lateinit var userHelper: UserHelper
    private lateinit var uriWithId: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val viewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val dataUser = intent.getParcelableExtra<User>(EXTRA_DETAIL)
        val id = dataUser?.id
        val photo = dataUser?.avatar_url
        val username = dataUser?.login
        val company = dataUser?.company


        dataUser?.let { viewModel.setData(it.login) }
        viewModel.getData().observe(this, {
            if (it != null) {
                dataUser?.avatar_url?.let { binding.detailPhoto.load(it) }
                binding.tvDetailName.text = it.name
                binding.tvDetailUsername.text = it.login
                binding.tvDetailCompany.text = it.company
                binding.tvRepositoryDetail.text = it.public_repos.toString()
                binding.tvFollowersDetail.text = it.followers.toString()
                binding.tvFollowingDetail.text = it.following.toString()
            }
        })

        binding.buttonBack.setOnClickListener{
            finish()
        }

        var statusFavorite = false

        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()

        val cursor: Cursor = userHelper.queryById(id.toString())
        if (cursor.moveToNext()) {
            statusFavorite = true
            setStatusFavorite(statusFavorite)
        }

        setStatusFavorite(statusFavorite)
        binding.buttonFav.setOnClickListener {
            if (!statusFavorite) {
                val values = contentValuesOf(
                        UserContract.UserColumn._ID to id,
                        UserContract.UserColumn.USERNAME to username,
                        UserContract.UserColumn.COMPANY to company,
                        UserContract.UserColumn.AVATAR to photo
                )
                contentResolver.insert(CONTENT_URI,values)
                statusFavorite = !statusFavorite
                setStatusFavorite(statusFavorite)
            } else {
                uriWithId = Uri.parse("$CONTENT_URI/$id")
                contentResolver.delete(uriWithId, null, null)
                statusFavorite = !statusFavorite
                setStatusFavorite(statusFavorite)
            }
        }

        val viewPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPagerAdapter.username = dataUser?.login
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.buttonFav.setImageResource(R.drawable.ic_favorite_red)
        } else {
            binding.buttonFav.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}