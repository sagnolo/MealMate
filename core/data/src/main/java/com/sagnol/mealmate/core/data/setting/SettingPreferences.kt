package com.sagnol.core.data.setting

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStroe by preferencesDataStore(name = "settings")

class SettingPreferences {
}