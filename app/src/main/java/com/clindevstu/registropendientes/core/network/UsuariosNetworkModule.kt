package com.clindevstu.registropendientes.core.network

import com.clindevstu.registropendientes.core.common.GlobalVars
import com.clindevstu.registropendientes.data.services.RegistroRecojoService
import com.clindevstu.registropendientes.data.services.UsuariosService
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
object UsuariosNetworkModule {

    @Provides
    @Singleton
    @Named("UsuarioClient")
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // Agrega interceptores si necesitas
            .build()
    }

    @Provides
    @Singleton
    @Named("UsuarioRetrofit")
    fun provideRetrofit(@Named("UsuarioClient") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GlobalVars.USUARIOS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideRegistroInternetService(@Named("UsuarioRetrofit") retrofit: Retrofit): UsuariosService {
        return retrofit.create(UsuariosService::class.java)
    }
}