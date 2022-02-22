package com.example.search_filteramproom_paging.UI.AllUserActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.search_filteramproom_paging.R
import com.example.search_filteramproom_paging.databinding.ActivityAllUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllUsersActivity : AppCompatActivity() {
    private var _binding:ActivityAllUsersBinding?=null
    private val binding:ActivityAllUsersBinding get() = _binding!!
    private val allUserVM:AllUserViewModel by viewModels()
    private val userPagingAdapter: UserPagingDataAdapter by lazy { UserPagingDataAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityAllUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
       /* allUserVM.usersList.asLiveData().observe(this, {
            val adapter=UserAdapter()
            adapter.submitList(it)
            binding.usersRV.adapter=adapter
        })*/
        binding.usersRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=userPagingAdapter
        }
        lifecycleScope.launch {
            allUserVM.newAndroidPagination.collectLatest  {
                userPagingAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
        this.clearFindViewByIdCache()
    }
}