package com.example.search_filteramproom_paging.model

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Query("select * from UserDB")
    fun getUsers():Flow<List<User>>
    @Query("select * from UserDB where id=:id")
    fun getUserById(id:Int):Flow<User>//get data without Paging
    @Query("delete from UserDB")
    fun removeAll()
    @Query("SELECT * FROM UserDB WHERE id IN (SELECT id FROM UserDB ORDER BY RANDOM() LIMIT :pageSize) ")
//    @Query("select * from UserDB LIMIT :pageSize")
    fun getUsers(pageSize:Int):List<User>
    @Query("select * from UserDB ") //oldWay
    fun getPagedUsers(): DataSource.Factory<Int, User>
    @Query("select * from UserDB ") //new
    fun getPagedUsersNew(): PagingSource<Int, User>
    @Query("select * from UserDB ") //new android
    fun getPagedUsersNewAndroid(): DataSource.Factory<Int, User>
    @Query("SELECT * FROM UserDB LIMIT :count OFFSET :offset")
    fun getDB(count:Int,offset:Int)
}