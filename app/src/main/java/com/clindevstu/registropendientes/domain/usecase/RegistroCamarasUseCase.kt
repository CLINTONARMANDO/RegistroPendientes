package com.clindevstu.registropendientes.domain.usecase

import com.clindevstu.registropendientes.core.models.requests.RegistroCamarasRequest
import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasCompletoResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroInternetSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroResponse
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
        try {
            val response = service.obtenerPaginados(
                page = page,
                limit = limit,
                descen = descen
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.success(it)
                }
                return Result.failure(Throwable("Respuesta vacía"))
            } else {
                return Result.failure(Throwable("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun obtenerPaginados(tecnico: String): Result<PaginationResponse<RegistroCamarasSimpleResponse>> {
        return try {
            val response = service.obtenerPorTecnico(tecnico = tecnico)
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

    suspend fun obtenerPorId(id: String): Result<RegistroCamarasCompletoResponse?>{
        return try {
            val response = service.obtenerPorId(action = "getById", id = id)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun crearNuevoRegistro(registroRequest: RegistroCamarasRequest): Result<RegistroResponse?>{
        return try {
            val response = service.registrarNuevo(registroRequest)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
