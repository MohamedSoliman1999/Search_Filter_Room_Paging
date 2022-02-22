package com.example.search_filteramproom_paging.utility

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.search_filteramproom_paging.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferences(private val context: Context) {
    private val Context.dataStorePreferences: DataStore<Preferences> by preferencesDataStore(
        name = "Settings"
    )
    suspend fun saveString(key:String,value:String){
        context.dataStorePreferences.edit {
            it[stringPreferencesKey(key)]=value
        }
    }
    fun getString(key:String):Flow<String> {
        return context.dataStorePreferences.data.map {
            it[stringPreferencesKey(key)]?:"No Data Found"
        }
    }

    suspend fun saveUser(user: User){
        context.dataStorePreferences.edit { preferences ->
            preferences[stringPreferencesKey("firstName")]=user.firstName
            preferences[stringPreferencesKey("lastName")]=user.lastName
            preferences[intPreferencesKey("age")]=user.age
            preferences[intPreferencesKey("id")]=user.id!!
        }
    }
    fun getUser():Flow<User> =
        context.dataStorePreferences.data.map {preferences->
            User(
                id =preferences[intPreferencesKey("id")]?:0,
                firstName = preferences[stringPreferencesKey("firstName")]?:"",
                lastName = preferences[stringPreferencesKey("lastName")]?:"",
                age = preferences[intPreferencesKey("age")]?:0
            )
        }
}