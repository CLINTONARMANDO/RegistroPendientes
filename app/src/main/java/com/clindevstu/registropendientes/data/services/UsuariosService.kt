package com.clindevstu.registropendientes.data.services

import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UsuariosService {

    @FormUrlEncoded
    @POST("exec") // Solo 'exec' porque baseUrl ya tiene el script completo hasta antes de /exec
    suspend fun loginUsuario(
        @Field("nombre") nombre: String,
        @Field("contrasena") contrasena: String
    ): Response<UsuarioResponse>
}
