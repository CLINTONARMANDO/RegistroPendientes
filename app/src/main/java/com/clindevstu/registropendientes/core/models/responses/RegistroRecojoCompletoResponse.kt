package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class RegistroRecojoCompletoResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("codigoUsuario") val codigoUsuario: String?,
    @SerializedName("nombreCliente") val nombreCliente: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("motivoRecojo") val motivo: String?,
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("lugar") val ciudadZona: String?,  // "lugar" en JSON, mapeado a ciudadZona
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("dispositivo") val tipoRegistro: String?,
    @SerializedName("fechaRegistro") val fechaRegistro: String?,
    @SerializedName("fechaRecojo") val fechaRecojo: String?,
    @SerializedName("dia") val paraElDia: String?,
    @SerializedName("tecnico") val tecnico: String?,
    @SerializedName("estado") val estado: String?
)
