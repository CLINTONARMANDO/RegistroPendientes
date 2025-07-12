package com.clindevstu.registropendientes.domain.usecase

import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasSimpleResponse
import com.clindevstu.registropendientes.data.services.RegistroCamarasService
import javax.inject.Inject

class RegistroCamarasUseCase @Inject constructor(
    private val service: RegistroCamarasService
) {

    suspend fun obtenerPaginados(
        page: Int,
        limit: Int,
        descen: Int = 1
    ): Result<PaginationResponse<RegistroCamarasSimpleResponse>> {
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

    suspend fun obtenerPorTecnico(tecnico: String): Result<List<RegistroCamarasSimpleResponse>> {
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
