package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class RegistroCamarasSimpleResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("tipo") val tipo_registro: String?,
    @SerializedName("lugar") val ciudad_zona: String?,
    @SerializedName("fecha_registro") val fecha_registro: String?,
    @SerializedName("dia") val para_el_dia: String?,
    @SerializedName("asesor") val asesor: String?,
    @SerializedName("nombreCliente") val nombre_cliente: String?,
    @SerializedName("dni") val dni: String?,
    @SerializedName("celular") val celular: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("productos") val productos: String?,
    @SerializedName("total") val total: String?,
    @SerializedName("adelanto") val adelanto: String?,
    @SerializedName("saldo") val saldo: String?
)
