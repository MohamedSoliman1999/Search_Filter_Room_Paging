package com.example.search_filteramproom_paging.UI.AllUserActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.search_filteramproom_paging.R
import com.example.search_filteramproom_paging.databinding.UserItemBinding
import com.example.search_filteramproom_paging.model.User
import com.example.search_filteramproom_paging.model.UserDiffUtilCallBack

class UserAdapter: ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffUtilCallBack) {

   inner class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(user: User) {
           binding.user=user
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup   , viewType: Int): UserViewHolder {
        val binding:UserItemBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_item,
            parent,
            false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}