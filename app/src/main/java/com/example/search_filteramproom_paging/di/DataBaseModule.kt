package com.example.search_filteramproom_paging.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.search_filteramproom_paging.model.User
import com.example.search_filteramproom_paging.model.UserDB
import com.example.search_filteramproom_paging.model.UserDao
import com.example.search_filteramproom_paging.utility.Keys.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideUserDB(
        @ApplicationContext context: Context,
        roomCallback: RoomDatabase.Callback
    ):UserDB {
        return Room.databaseBuilder(context,UserDB::class.java,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
//            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideRoomCallBack():RoomDatabase.Callback= object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }

            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                super.onDestructiveMigration(db)
            }
        }
    @Provides
    @Singleton
    fun provideDao(db: UserDB): UserDao {
        return db.getUserDao()
    }
}