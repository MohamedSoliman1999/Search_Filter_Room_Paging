package com.example.search_filteramproom_paging.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.arch.core.executor.ArchTaskExecutor
import com.example.search_filteramproom_paging.model.User
import com.example.search_filteramproom_paging.model.UserDao
import javax.inject.Inject

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.search_filteramproom_paging.model.UserPagingSource
import com.example.search_filteramproom_paging.model.UserPagingSource.Companion.USER_PAGE_SIZE
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow


open class UserRepository @Inject constructor(
    val userDao: UserDao,
    var appContext: Context
) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    fun getAllUsers() = userDao.getUsers()
    suspend fun removeAll() {
        userDao.removeAll()
    }

    //    getPaging data from Room.
    fun getPagedList(config: PagedList.Config): LiveData<PagedList<User>> {
        val factory: DataSource.Factory<Int, User> = userDao.getPagedUsers()
        return LivePagedListBuilder(factory, config)
            .build()
    }

    //    getPaging data from Room.
    fun getNewPagedList(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<User>> {
        val pagingSourceFactory = { userDao.getPagedUsersNew() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = USER_PAGE_SIZE, enablePlaceholders = false)
    }

    @SuppressLint("RestrictedApi")
    fun getPageListAndroid(): Flow<PagingData<User>> {
        return Pager(
            PagingConfig(4),
            null,
            userDao.getPagedUsersNewAndroid().asPagingSourceFactory(
                ArchTaskExecutor.getIOThreadExecutor().asCoroutineDispatcher()
            )
        ).flow

    }
}