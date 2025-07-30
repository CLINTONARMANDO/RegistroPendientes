package com.clindevstu.registropendientes.domain.usecase

import android.util.Log
import com.clindevstu.registropendientes.core.models.responses.AppDataResponse
import com.clindevstu.registropendientes.core.models.responses.ErrorResponse
import com.clindevstu.registropendientes.data.services.AppDataService
import javax.inject.Inject

class AppDataUseCase @Inject constructor(
    private val appDataService: AppDataService
) {
    suspend operator fun invoke(): ErrorResponse<AppDataResponse> {

        Log.d("invoke", "llamada")
        return appDataService.obtenerDataApp()
    }
}