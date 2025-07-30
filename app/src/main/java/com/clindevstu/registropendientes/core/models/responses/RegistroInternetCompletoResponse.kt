package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class RegistroInternetCompletoResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("codigo_grande") val codigoGrande: String?,
    @SerializedName("asesor_ventas") val asesorVentas: String?,
    @SerializedName("empresa") val empresa: String?,
    @SerializedName("fecha_registro") val fechaRegistro: String?,
    @SerializedName("nombre_cliente") val nombreCliente: String?,
    @SerializedName("dni") val dni: String?,
    @SerializedName("celulares") val celulares: String?,
    @SerializedName("direccion") val direccion: String?,
    @SerializedName("lugar") val lugar: String?,
    @SerializedName("dia_instalacion") val diaInstalacion: String?,
    @SerializedName("otros_detalles") val otrosDetalles: String?,
    @SerializedName("tipo") val tipo: String?,
    @SerializedName("prioridad") val prioridad: String?,
    @SerializedName("tecnico") val tecnico: String?,
    @SerializedName("estado") val estado: String?,
    @SerializedName("plan") val plan: String?,
    @SerializedName("costo_plan") val costoPlan: String?,
    @SerializedName("costo_inst") val costoInst: String?,
    @SerializedName("promocion") val promocion: String?,
    @SerializedName("kit_antena") val kitAntena: String?,
    @SerializedName("costo_kit") val costoKit: String?,
    @SerializedName("costo_antena") val costoAntena: String?,
    @SerializedName("dias") val dias: String?,
    @SerializedName("adelanto") val adelanto: String?,
    @SerializedName("total") val total: String?
)