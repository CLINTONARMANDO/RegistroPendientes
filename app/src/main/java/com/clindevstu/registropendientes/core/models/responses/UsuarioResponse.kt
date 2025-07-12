package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class UsuarioResponse(
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("esTecnico") val esTecnico: Int?,
    @SerializedName("nombreTecnico") val nombreTecnico: String?,
    @SerializedName("token") val token: String? // Si no se pudo ingresar
)