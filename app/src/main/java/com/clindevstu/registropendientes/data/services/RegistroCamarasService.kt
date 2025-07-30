package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.requests.RegistroCamarasRequest
import com.clindevstu.registropendientes.core.models.requests.RegistroInternetRequest
import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasCompletoResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RegistroCamarasService {
    @GET("exec")
    suspend fun obtenerPaginados(
        @Query("action") action: String = "list",
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("descen") descen: Int = 1
    ): Response<PaginationResponse<RegistroCamarasSimpleResponse>>

    @GET("exec")
    suspend fun obtenerPorId(
        @Query("action") action: String = "getById",
        @Query("id") id: String
    ): Response<RegistroCamarasCompletoResponse>

    @GET("exec")
    suspend fun obtenerPorTecnico(
        @Query("action") action: String = "getByTecnico",
        @Query("tecnico") tecnico: String
    ): Response<PaginationResponse<RegistroCamarasSimpleResponse>>

    @POST("exec")
    suspend fun registrarNuevo(
        @Body request: RegistroCamarasRequest
    ): Response<RegistroResponse>
}