package com.clindevstu.registropendientes.domain.usecase

import android.util.Log
import com.clindevstu.registropendientes.core.models.responses.ErrorResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.services.UsuariosService
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val usuariosService: UsuariosService
) {
    suspend operator fun invoke(nombre: String, contrasena: String): ErrorResponse<UsuarioResponse> {

        Log.d("invoke", "llamada")
        return usuariosService.loginUsuario(nombre, contrasena)
    }
}