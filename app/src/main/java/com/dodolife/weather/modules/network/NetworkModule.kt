package com.dodolife.weather.modules.network

import android.content.Context
import android.util.Log
import com.dodolife.weather.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    private val timeOut: Int by lazy {
        60
    }

    private val responseInterceptor by lazy {
        ResponseInterceptor
    }

    private val jsonChecker by lazy {
        object : Converter.Factory() {
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<Annotation>,
                retrofit: Retrofit
            ): Converter<ResponseBody, *>? {
                return Converter<ResponseBody, Any> { responseBody ->
                    val delegate =
                        retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
                    try {
                        delegate.convert(responseBody)
                    } catch (error: Exception) {
                        error.message?.let { Log.e("quotes ", it) }
                        throw IOException("Terjadi kesalahan pada server")
                    }
                }
            }
        }
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("context.getString(R.string.server_url)")
            .addConverterFactory(jsonChecker)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .also {
                ResponseInterceptor.retrofit = it
//                ResponseInterceptor.getToken = accountManager.getToken()
            }
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(responseInterceptor)
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }
        return builder.build()
    }
//
//    @Provides
//    @Singleton
//    fun providesAuthServices(retrofit: Retrofit): AuthServices {
//        return retrofit.create(AuthServices::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun providesMainServices(retrofit: Retrofit): MainServices {
//        return retrofit.create(MainServices::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun providesTvServices(retrofit: Retrofit): TvServices {
//        return retrofit.create(TvServices::class.java)
//    }
}
