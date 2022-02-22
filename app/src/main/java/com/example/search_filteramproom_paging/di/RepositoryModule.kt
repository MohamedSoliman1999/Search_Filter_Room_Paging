package com.example.search_filteramproom_paging.di

import android.content.Context
import com.example.search_filteramproom_paging.model.UserDao
import com.example.search_filteramproom_paging.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        @ApplicationContext context: Context,
        userDao: UserDao
    ): UserRepository =UserRepository(userDao,context)

}