package com.example.search_filteramproom_paging.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class UserPagingSource(val userDao: UserDao) : PagingSource<Int, User>() {
    companion object {
        private const val INITIAL_PAGE_INDEX = 0
        const val USER_PAGE_SIZE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        var position = params.key ?: INITIAL_PAGE_INDEX
        val userList = userDao.getUsers(params.loadSize)
        Log.e("UserPagingSource","$position   ${params.loadSize}")
        return try {
            LoadResult.Page(
                data = userList,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position-1,
                nextKey = if (userList.isNullOrEmpty()) null else position+1
            )
        }catch (e: Exception){
            Log.e("UserPagingSource", e.message.toString())
            LoadResult.Error(e)
    }
}

}