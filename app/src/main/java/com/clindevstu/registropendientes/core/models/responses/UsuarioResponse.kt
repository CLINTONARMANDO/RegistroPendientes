package com.clindevstu.registropendientes.core.models.responses

data class UsuarioResponse(
    val nombre: String?,
    val esTecnico: Int?,
    val nombreTecnico: String?,
    val mensaje: String? // Si no se pudo ingresar
)