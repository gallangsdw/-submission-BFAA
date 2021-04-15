package com.sdwtech.consumerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.sdwtech.consumerapp.databinding.ItemUserBinding
import com.sdwtech.consumerapp.data.User


class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private val listUser = ArrayList<User>()

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
            }
        }
    }