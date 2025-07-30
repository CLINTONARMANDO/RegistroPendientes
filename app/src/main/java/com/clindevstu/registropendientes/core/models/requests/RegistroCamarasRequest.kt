package com.clindevstu.registropendientes.core.models.requests

import com.google.gson.annotations.SerializedName

data class RegistroCamarasRequest(
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("tipo") val tipo: String?,
    @SerializedName("lugar") val lugar: String?,
    @SerializedName("fecha_registro") val fechaRegistro: String?,
    @SerializedName("dia") val dia: String?,
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("nombreCliente") val nombreCliente: String?,
    @SerializedName("dni") val dni: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("celular2") val celular2: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("referencia") val referencia: String?,
    @SerializedName("productos") val productos: String?,
    @SerializedName("total") val total: String?,
    @SerializedName("adelanto") val adelanto: String?,
    @SerializedName("saldo") val saldo: String?
)
