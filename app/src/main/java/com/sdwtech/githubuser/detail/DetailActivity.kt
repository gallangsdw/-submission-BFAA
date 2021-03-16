package com.sdwtech.githubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.databinding.ActivityDetailBinding

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

        val dataUser = intent.getParcelableExtra<User>(EXTRA_DETAIL)

        dataUser?.photo?.let { binding.detailPhoto.load(it) }
        binding.tvDetailName.text = dataUser?.name
        binding.tvDetailUsername.text = dataUser?.username
        binding.tvDetailCompany.text = dataUser?.company

        binding.buttonBack.setOnClickListener{
            finish()
        }
    }
}