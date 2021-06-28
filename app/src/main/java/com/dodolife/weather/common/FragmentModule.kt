package com.dodolife.weather.common

import androidx.fragment.app.FragmentFactory
import com.dodolife.weather.common.fragment.MainFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {

    @Singleton
    @Provides
    fun provideMainFragmentFactory(): FragmentFactory {
        return MainFragmentFactory()
    }
}





