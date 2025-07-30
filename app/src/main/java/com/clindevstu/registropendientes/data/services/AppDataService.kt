package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.responses.AppDataResponse
import com.clindevstu.registropendientes.core.models.responses.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AppDataService {

    @GET("exec")
    suspend fun obtenerDataApp(
        @Query("id") id: String = "1",
    ): ErrorResponse<AppDataResponse>
}