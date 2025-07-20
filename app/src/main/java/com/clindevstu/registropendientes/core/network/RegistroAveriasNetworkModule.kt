package com.clindevstu.registropendientes.core.network

import com.clindevstu.registropendientes.core.common.GlobalVars
import com.clindevstu.registropendientes.data.services.RegistroAveriasService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistroAveriasNetworkModule {

    @Provides
    @Singleton
    @Named("AveriasClient")
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // Agrega interceptores si necesitas
            .build()
    }

    @Provides
    @Singleton
    @Named("AveriasRetrofit")
    fun provideRetrofit(@Named("AveriasClient") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GlobalVars.REGISTROAVERIAS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRegistroInternetService(@Named("AveriasRetrofit") retrofit: Retrofit): RegistroAveriasService {
        return retrofit.create(RegistroAveriasService::class.java)
    }
}