package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class RegistroRecojoSimpleResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("codigo_usuario") val codigoUsuario: String?,
    @SerializedName("nombre_cliente") val nombreCliente: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("motivo_recojo") val motivo: String?,
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("ciudad_zona") val ciudadZona: String?,
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("dispositivo") val tipoRegistro: String?,
    @SerializedName("fecha_registro") val fechaRegistro: String?,
    @SerializedName("fecha_recojo") val fechaRecojo: String?,
    @SerializedName("dia") val paraElDia: String?,
    @SerializedName("tecnico") val tecnico: String?,
    @SerializedName("estado") val estado: String?
)
