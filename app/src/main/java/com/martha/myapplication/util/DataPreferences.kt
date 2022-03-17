package com.martha.myapplication.util

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataPreferences(
    context: Context
) {
    private val appContext = context.applicationContext
    private val dataStore : DataStore<Preferences>

    init {
        dataStore = appContext.createDataStore(
            name = "pref_data_store"
        )
    }

    val getToken: Flow<String>
        get() = dataStore.data.map { p ->
            p[TOKEN] ?: ""
        }

    suspend fun setToken(value: String){
        dataStore.edit { p ->
            p[TOKEN] = value
        }
    }

    val getUsername: Flow<String>
        get() = dataStore.data.map { p ->
            p[USERNAME] ?: ""
        }

    suspend fun setUsername(value: String){
        dataStore.edit { p ->
            p[USERNAME] = value
        }
    }

    val getAccountNo: Flow<String>
        get() = dataStore.data.map { p ->
            p[ACCOUNT_NO] ?: ""
        }

    suspend fun setAccountNo(value: String){
        dataStore.edit { p ->
            p[ACCOUNT_NO] = value
        }
    }

    companion object{
        private val TOKEN = preferencesKey<String>("token")
        private val USERNAME = preferencesKey<String>("username")
        private val ACCOUNT_NO = preferencesKey<String>("accountno")
    }
}