package com.dodolife.weather.modules

import android.content.Context
import androidx.room.Room
import com.dodolife.weather.database.KraDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesRoom(@ApplicationContext context: Context): KraDb {
        return Room.databaseBuilder(context, KraDb::class.java, "kra.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}