package com.clindevstu.registropendientes.domain.usecase

import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroAveriasSimpleResponse
import com.clindevstu.registropendientes.data.services.RegistroAveriasService
import javax.inject.Inject

class RegistroAveriasUseCase @Inject constructor(
    private val service: RegistroAveriasService
) {

    suspend fun obtenerPaginados(
        page: Int,
        limit: Int,
        descen: Int = 1
    ): Result<PaginationResponse<RegistroAveriasSimpleResponse>> {
        return try {
            val response = service.obtenerPaginados(page = page, limit = limit, descen = descen)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerPorTecnico(tecnico: String): Result<List<RegistroAveriasSimpleResponse>> {
        return try {
            val response = service.obtenerPorTecnico(tecnico = tecnico)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Lista vacía"))
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}