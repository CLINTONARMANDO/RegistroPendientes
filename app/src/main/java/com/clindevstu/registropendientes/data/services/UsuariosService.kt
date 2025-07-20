package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.responses.ErrorResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuariosService {

    @GET("exec")
    suspend fun loginUsuario(
        @Query("nombre") nombre: String,
        @Query("contrasena") contrasena: String
    ): ErrorResponse<UsuarioResponse>
}
