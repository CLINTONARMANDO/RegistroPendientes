package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.responses.RegistroRecojoResponse
import retrofit2.http.*
import retrofit2.Response

interface RegistroRecojoService {

//    @POST("SCRIPT_ID/exec") // reemplaza SCRIPT_ID por tu ID real o base URL configurada
//    suspend fun registrarRecojo(
//        @Body request: RegistroRecojoRequest
//    ): Response<String>

    @GET("exec")
    suspend fun obtenerRecojosPaginados(
        @Query("action") action: String = "list",
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("descen") descen: Int = 1
    ): Response<PaginatedRecojosResponse>

    @GET("exec")
    suspend fun obtenerRecojoPorId(
        @Query("action") action: String = "getById",
        @Query("id") id: String
    ): Response<RegistroRecojoResponse>

    @GET("exec")
    suspend fun obtenerRecojosPorTecnico(
        @Query("action") action: String = "getByTecnico",
        @Query("tecnico") tecnico: String
    ): Response<List<RegistroRecojoResponse>>
}
