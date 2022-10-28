package com.heechan.membeder.util

import android.content.Context
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

val Context.dataStore by preferencesDataStore(name = "dataStore")

class DataStoreUtil(val context: Context) {

    companion object {
        val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    val accessToken = context.dataStore.data.map{
        it[KEY_ACCESS_TOKEN] ?: ""
    }

    suspend fun setAccessToken(accessToken : String) {
        context.dataStore.edit {
            it[KEY_ACCESS_TOKEN] = accessToken
        }
    }
}