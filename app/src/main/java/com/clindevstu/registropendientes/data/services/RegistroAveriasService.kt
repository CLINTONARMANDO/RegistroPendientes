package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroAveriasSimpleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RegistroAveriasService {
    @GET("exec")
    suspend fun obtenerPaginados(
        @Query("action") action: String = "list",
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("descen") descen: Int = 1
    ): Response<PaginationResponse<RegistroAveriasSimpleResponse>>

//    @GET("exec")
//    suspend fun obtenerPorId(
//        @Query("action") action: String = "getById",
//        @Query("id") id: String
//    ): Response<RegistroAveriasCompletoResponse>

    @GET("exec")
    suspend fun obtenerPorTecnico(
        @Query("action") action: String = "getByTecnico",
        @Query("tecnico") tecnico: String
    ): Response<List<RegistroAveriasSimpleResponse>>
}