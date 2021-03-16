package com.sdwtech.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdwtech.githubuser.adapter.UserAdapter
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.data.UserData
import com.sdwtech.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val userAdapter = UserAdapter(list)
        list.addAll(UserData.listData)

        with(binding.rvUser){
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userAdapter
            userAdapter.notifyDataSetChanged()
        }
    }
}