package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class RegistroAveriasCompletoResponse (
    @SerializedName("id") val id: String?,
    @SerializedName("codigo_averia") val codigoRegistro: String?,
    @SerializedName("nombre_cliente") val nombreCliente: String?,
    @SerializedName("codigo_usuario") val codigoUsuario: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("tipo_falla") val descripcion: String?,
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("ciudad_zona") val ciudadZona: String?,
    @SerializedName("fecha_registro") val fechaRegistro: String?,
    @SerializedName("prioridad") val prioridad: String?,
    @SerializedName("tipo") val tipoRegistro: String?,
    @SerializedName("para_el_dia") val paraElDia: String?,
    @SerializedName("tecnico") val tecnico: String?,
    @SerializedName("estado") val estado: String?
)