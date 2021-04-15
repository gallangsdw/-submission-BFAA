package com.sdwtech.githubuser.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sdwtech.githubuser.R
import com.sdwtech.githubuser.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonBack.setOnClickListener{
            finish()
        }

        supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferenceFragment()).commit()
    }
}