package com.sagnol.mealmate.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.sagnol.mealmate.core.data.repository.SettingRepositoryImpl
import com.sagnol.mealmate.core.domain.repository.SettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {
    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            appContext.preferencesDataStoreFile("user_settings")
        }
    }

    @Provides
    @Singleton
    fun provideSettingRepository(
        dataStore: DataStore<Preferences>
    ): SettingRepository {
        return SettingRepositoryImpl(dataStore)
    }
}