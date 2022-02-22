package com.example.search_filteramproom_paging.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.search_filteramproom_paging.utility.Keys.USER_DB_VERSION

@Database(entities = [User::class], version =USER_DB_VERSION,exportSchema = true)
abstract class UserDB: RoomDatabase() {
    abstract fun getUserDao():UserDao
}