package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class AppDataResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("ultima_version") val ultimaVersion: Double,
    @SerializedName("ultima_version_permitida") val ultimaVersionPermitida: Double
)