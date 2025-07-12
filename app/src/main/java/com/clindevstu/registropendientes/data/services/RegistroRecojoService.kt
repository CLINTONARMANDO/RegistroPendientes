package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroRecojoSimpleResponse
import retrofit2.http.*
import retrofit2.Response

interface RegistroRecojoService {

//    @POST("SCRIPT_ID/exec") // reemplaza SCRIPT_ID por tu ID real o base URL configurada
//    suspend fun registrarRecojo(
//        @Body request: RegistroRecojoRequest
//    ): Response<String>

    @GET("exec")
    suspend fun obtenerPaginados(
        @Query("action") action: String = "list",
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("descen") descen: Int = 1
    ): Response<PaginationResponse<RegistroRecojoSimpleResponse>>

//    @GET("exec")
//    suspend fun obtenerPorId(
//        @Query("action") action: String = "getById",
//        @Query("id") id: String
//    ): Response<RegistroRecojoSimpleResponse>

    @GET("exec")
    suspend fun obtenerPorTecnico(
        @Query("action") action: String = "getByTecnico",
        @Query("tecnico") tecnico: String
    ): Response<List<RegistroRecojoSimpleResponse>>
}
