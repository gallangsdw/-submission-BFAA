package com.sdwtech.githubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sdwtech.githubuser.data.User
import com.sdwtech.githubuser.databinding.ItemUserBinding
import com.sdwtech.githubuser.detail.DetailActivity

class UserAdapter(private val listUser: ArrayList<User>) :
        RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    fun setData(list: ArrayList<User>) {
        listUser.clear()
        listUser.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    class ViewHolder(private val binding: ItemUserBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(user: User) {
                binding.tvItemName.text = user.name
                binding.tvItemUsername.text = user.login
                binding.tvCompany.text = user.company
                binding.imgItemPhoto.load(user.avatar_url) {
                    transformations(RoundedCornersTransformation(20f))
                }

                itemView.setOnClickListener {
                    val moveToDetail = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DETAIL, user)
                    }
                    itemView.context.startActivity(moveToDetail)
                }
            }
        }
    }