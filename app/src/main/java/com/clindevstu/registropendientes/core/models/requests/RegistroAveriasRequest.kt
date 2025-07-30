package com.clindevstu.registropendientes.core.models.requests

import com.google.gson.annotations.SerializedName

data class RegistroAveriasRequest(
    @SerializedName("codigo_grande") val codigoGrande: String?,
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("tipo") val tipo: String?,
    @SerializedName("nombre_cliente") val nombreCliente: String?,
    @SerializedName("codigo_cliente") val codigoCliente: String?,
    @SerializedName("direccion") val direccion: String?, // sin concatenar aún
    @SerializedName("referencia") val referencia: String?,
    @SerializedName("lugar") val lugar: String?,
    @SerializedName("celular") val celular: String?,     // se concatenan en el backend
    @SerializedName("celular2") val celular2: String?,
    @SerializedName("fecha_registro") val fechaRegistro: String?,
    @SerializedName("interaccion") val interaccion: String?,
    @SerializedName("prioridad") val prioridad: String?
)
