package com.sagnol.core.data.di

import android.content.Context
import androidx.room.Room
import com.sagnol.core.data.database.AppDatabase
import com.sagnol.core.data.database.MealDao
import com.sagnol.core.data.repository.MealRepositoryImpl
import com.sagnol.core.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "mealmate-db"
    ).build()

    @Provides
    fun provideMealDao(db: AppDatabase): MealDao = db.mealDao()

    @Provides
    fun provideMealRepository(
        mealDao: MealDao
    ): MealRepository = MealRepositoryImpl(mealDao)
}