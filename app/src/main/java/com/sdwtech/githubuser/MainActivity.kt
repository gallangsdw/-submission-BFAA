package com.sdwtech.githubuser

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdwtech.githubuser.adapter.UserAdapter
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.databinding.ActivityMainBinding
import com.sdwtech.githubuser.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter
    private val listUser = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        userAdapter = UserAdapter()

        viewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        showUser()
        searchUser()

        with(binding.rvUser){
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.rvUser.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                query?.let { viewModel.setSearch(it) }

                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun showUser() {
        viewModel.setData()
        viewModel.getData().observe(this, {
            if (it != null) {
                userAdapter.setData(it)
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun searchUser() {
        viewModel.getSearch().observe(this, {
            if (it != null && it.size != 0) {
                userAdapter.setData(it)
                binding.progressBar.visibility = View.GONE
                binding.rvUser.visibility = View.VISIBLE
            }
            else {
                binding.progressBar.visibility = View.GONE
                binding.rvUser.visibility = View.GONE
            }
        })
    }
}