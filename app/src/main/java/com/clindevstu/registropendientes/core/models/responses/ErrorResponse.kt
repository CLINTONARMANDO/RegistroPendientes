package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class ErrorResponse <T> (
    @SerializedName("error") val error: Int,
    @SerializedName("mensaje") val mensaje: String,
    @SerializedName("data") val data: T
)