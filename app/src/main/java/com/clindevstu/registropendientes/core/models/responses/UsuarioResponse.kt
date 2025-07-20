package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class UsuarioResponse(
    @SerializedName("nombre") val nombre: String? = null,
    @SerializedName("esTecnico") val esTecnico: Boolean = false,
    @SerializedName("nombreTecnico") val nombreTecnico: String? = null,
    @SerializedName("token") val token: String? = null
)
