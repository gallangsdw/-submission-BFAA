package com.sdwtech.githubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.databinding.ActivityDetailBinding
import com.sdwtech.githubuser.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val viewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val dataUser = intent.getParcelableExtra<User>(EXTRA_DETAIL)

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
    }
}