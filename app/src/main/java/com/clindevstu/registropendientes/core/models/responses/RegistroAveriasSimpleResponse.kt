package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class RegistroAveriasSimpleResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("codigoAveria") val codigoRegistro: String?,
    @SerializedName("nombreCliente") val nombreCliente: String?,
    @SerializedName("codigoUsuario") val codigoUsuario: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("tipoFalla") val descripcion: String?,
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("ciudadZona") val ciudadZona: String?,
    @SerializedName("fechaRegistro") val fechaRegistro: String?,
    @SerializedName("tipoPrioridad") val prioridad: String?,
    @SerializedName("tipo") val tipoRegistro: String?,
    @SerializedName("paraElDia") val paraElDia: String?,
    @SerializedName("tecnico") val tecnico: String?,
    @SerializedName("estado") val estado: String?
)


