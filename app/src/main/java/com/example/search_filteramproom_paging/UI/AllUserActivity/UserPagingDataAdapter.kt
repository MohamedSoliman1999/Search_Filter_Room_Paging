package com.example.search_filteramproom_paging.UI.AllUserActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.search_filteramproom_paging.R
import com.example.search_filteramproom_paging.databinding.UserItemBinding
import com.example.search_filteramproom_paging.model.User
import com.example.search_filteramproom_paging.model.UserDiffUtilCallBack

class UserPagingDataAdapter: PagingDataAdapter<User, UserPagingDataAdapter.UserPagingViewHolder>(
    diffCallback = UserDiffUtilCallBack
) {

    inner class UserPagingViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position:Int){
            getItem(position)?.let {
                binding.user=it
            }

        }
    }

    override fun onBindViewHolder(holder: UserPagingViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPagingViewHolder {
        val itemBinding:UserItemBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_item,
            parent,
            false
        )
        return UserPagingViewHolder(itemBinding)
    }
}