package com.damikkg.test65appsfragments.di

import com.damikkg.test65appsfragments.data.remote.RemoteSourceImp
import com.damikkg.test65appsfragments.data.remote.webservice.WebService
import com.damikkg.test65appsfragments.domain.contracts.INetworkSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    fun provideGsonConverter():Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(gsonConverter:Converter.Factory, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://gitlab.65apps.com")
        .addConverterFactory(gsonConverter)
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Singleton
    @Provides
    fun provideNetworkSource(webService: WebService): INetworkSource = RemoteSourceImp(webService)
}