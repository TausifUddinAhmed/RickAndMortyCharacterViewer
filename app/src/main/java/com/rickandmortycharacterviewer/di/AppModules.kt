package com.rickandmortycharacterviewer.di

import com.rickandmortycharacterviewer.BuildConfig
import com.rickandmortycharacterviewer.domain.repository.RickyAndMortyRepository
import com.rickandmortycharacterviewer.network.repository.RickyAndMortyRepositoryImpl
import com.rickandmortycharacterviewer.network.service.APIService
import com.rickandmortycharacterviewer.utils.Const.KEY_API
import com.rickandmortycharacterviewer.utils.Const.PAGE_SIZE
import com.rickandmortycharacterviewer.utils.Const.WEB_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Named("WEB_API")
    fun provideWebAPI(): String = BuildConfig.BASE_URL

    @Provides
    @Named("KEY_API")
    fun provideKeyAPI(): String = KEY_API

    @Provides
    @Named("PAGE_SIZE")
    fun providePageSize(): Int = PAGE_SIZE

    @Provides
    fun provideRetrofit(
        @Named("WEB_API") webAPI: String,
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(webAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideAPIService(
        retrofit: Retrofit
    ): APIService = retrofit.create(APIService::class.java)

    @Provides
    fun provideRickyAndMortyRepository(
        apiService: APIService,
        @Named("KEY_API") keyApi: String,
        @Named("PAGE_SIZE") pageSize: Int,
    ): RickyAndMortyRepository = RickyAndMortyRepositoryImpl(
        APIService = apiService,
        pageSize = pageSize,
        apiKey = keyApi
    )
}