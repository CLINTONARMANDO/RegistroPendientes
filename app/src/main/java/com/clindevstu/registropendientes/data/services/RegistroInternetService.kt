package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.responses.RegistroInternetResponse
import retrofit2.http.GET
import retrofit2.Response

interface RegistroInternetService {
    @GET("exec")
    suspend fun getRegistrosInternet(): Response<List<RegistroInternetResponse>>





}
