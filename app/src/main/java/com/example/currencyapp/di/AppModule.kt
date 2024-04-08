package com.example.currencyapp.di

import com.example.currencyapp.data.remote.CurrencyApi
import com.example.currencyapp.data.remote.CurrencyApi.Companion.BASE_URL
import com.example.currencyapp.data.repository.CurrencyRepositoryImpl
import com.example.currencyapp.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    fun providesCurrencyApi() : CurrencyApi {
        val httpLoggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(CurrencyApi::class.java)
    }

    @Singleton
    @Provides
    fun providesCurrencyRepository(currencyApi: CurrencyApi) : CurrencyRepository {
        return CurrencyRepositoryImpl(currencyApi)
    }
}