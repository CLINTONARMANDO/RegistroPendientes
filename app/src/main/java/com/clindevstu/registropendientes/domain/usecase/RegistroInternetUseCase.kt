package com.clindevstu.registropendientes.domain.usecase

import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroInternetSimpleResponse
import com.clindevstu.registropendientes.data.services.RegistroInternetService
import javax.inject.Inject

class RegistroInternetUseCase @Inject constructor(
    private val service: RegistroInternetService
) {
    suspend fun obtenerPaginados(
        page: Int,
        limit: Int,
        descen: Int = 1
    ): Result<PaginationResponse<RegistroInternetSimpleResponse>> {
        return try {
            val response = service.obtenerPaginados(
                page = page,
                limit = limit,
                descen = descen
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Throwable("Respuesta vacía"))
            } else {
                Result.failure(Throwable("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerPorTecnico(tecnico: String): Result<List<RegistroInternetSimpleResponse>> {
        return try {
            val response = service.obtenerPorTecnico(
                tecnico = tecnico,
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Throwable("Respuesta vacía"))
            } else {
                Result.failure(Throwable("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}