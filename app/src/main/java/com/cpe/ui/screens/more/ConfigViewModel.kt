package com.cpe.ui.screens.more

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "config")

class ConfigViewModel : ViewModel() {
    private val darkModeKey = booleanPreferencesKey("dark_mode")
    private val levelKey = stringPreferencesKey("level")

    fun switchDarkModeValue(context: Context) {
        viewModelScope.launch {
            context.dataStore.edit { settings ->
                val currentDarkModeValue = settings[darkModeKey] ?: false
                settings[darkModeKey] = !currentDarkModeValue
            }
        }
    }

    fun getDarkModeValue(context: Context): Flow<Boolean> {
        val darkModeFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
            preferences[darkModeKey] ?: false
        }
        return darkModeFlow
    }

    fun switchLevelValue(context: Context, level: String) {
        viewModelScope.launch {
            context.dataStore.edit { settings ->
                settings[levelKey] = level
            }
        }
    }

    fun getLevelValue(context: Context): Flow<String> {
        val levelFlow: Flow<String> = context.dataStore.data.map { preferences ->
            preferences[levelKey] ?: "300"
        }
        return levelFlow
    }
}