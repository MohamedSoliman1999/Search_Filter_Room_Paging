package com.example.search_filteramproom_paging.UI.MainActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search_filteramproom_paging.model.User
import com.example.search_filteramproom_paging.model.UserDao
import com.example.search_filteramproom_paging.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainActivityViewModel @Inject constructor(
    private val userRepository: UserRepository
//    private val appContext: Context
): ViewModel() {
    fun insertUser(user: User)= CoroutineScope(Dispatchers.IO).launch{
        userRepository.insertUser(user)
    }
    fun removeAll()=CoroutineScope(Dispatchers.IO).launch{
        userRepository.removeAll()
    }
}