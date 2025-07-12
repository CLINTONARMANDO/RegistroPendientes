package com.clindevstu.registropendientes.core.models.responses

import com.google.gson.annotations.SerializedName

data class PaginationResponse <T>(
    @SerializedName("pageActual")
    val pageActual: Int,

    @SerializedName("totalPages")
    val totalPages: Int,

    @SerializedName("pageSize")
    val pageSize: Int,

    @SerializedName("totalItems")
    val totalItems: Int,

    @SerializedName("hasNextPage")
    val hasNextPage: Boolean,

    @SerializedName("hasPreviousPage")
    val hasPreviousPage: Boolean,

    @SerializedName("data")
    val data: List<T>
)