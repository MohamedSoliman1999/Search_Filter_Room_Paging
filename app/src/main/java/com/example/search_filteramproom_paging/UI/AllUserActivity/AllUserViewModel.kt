package com.example.search_filteramproom_paging.UI.AllUserActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.search_filteramproom_paging.model.User
import com.example.search_filteramproom_paging.model.UserPagingSource
import com.example.search_filteramproom_paging.model.UserPagingSource.Companion.USER_PAGE_SIZE
import com.example.search_filteramproom_paging.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AllUserViewModel @Inject constructor(
    private val userRepository: UserRepository
//    private val appContext: Context
) : ViewModel() {
    var usersList: Flow<List<User>> = getAllUsers()//get normal data from room list
    private fun getAllUsers() = userRepository.getAllUsers()
    val randomUserFlow = Pager(
        PagingConfig(
            pageSize = USER_PAGE_SIZE,
            enablePlaceholders = true,
            initialLoadSize = 11
        )
    ) { UserPagingSource(userRepository.userDao) }
        .flow
        .cachedIn(viewModelScope)//get data from room using paging

    //    old pagination
    val livePagedUsers = userRepository.getPagedList(
        PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(11)
//    .setPrefetchDistance(0)
            .setEnablePlaceholders(false)
            .build()
    )
//-------------------------------

    val newAndroidPagination=userRepository.getPageListAndroid()
}