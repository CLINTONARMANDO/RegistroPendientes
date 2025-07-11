package com.clindevstu.registropendientes.core.network

import com.clindevstu.registropendientes.core.common.GlobalVars
import com.clindevstu.registropendientes.data.services.RegistroCamarasService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistroCamarasNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // Agrega interceptores si necesitas
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GlobalVars.REGISTROCAMARAS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRegistroInternetService(retrofit: Retrofit): RegistroCamarasService {
        return retrofit.create(RegistroCamarasService::class.java)
    }
}