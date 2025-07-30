package com.clindevstu.registropendientes.core.models.requests

import com.google.gson.annotations.SerializedName

data class RegistroRecojoRequest(
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("codigoUsuario") val codigoUsuario: String?,
    @SerializedName("nombreCliente") val nombreCliente: String?,
    @SerializedName("lugar") val lugar: String?,
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("dispositivo") val dispositivo: String?,
    @SerializedName("fechaRegistro") val fechaRegistro: String?,
    @SerializedName("fechaRecojo") val fechaRecojo: String?,
    @SerializedName("dia") val dia: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("celular2") val celular2: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("referencia") val referencia: String?,
    @SerializedName("motivoRecojo") val motivoRecojo: String?
)