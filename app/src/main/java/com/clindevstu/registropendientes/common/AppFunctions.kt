package com.clindevstu.registropendientes.common

import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale


object AppFunctions {

    fun formatearFecha(fechaIso: String?): String {
        if (fechaIso.isNullOrBlank()) return "Sin fecha"
        return try {
            val fecha = ZonedDateTime.parse(fechaIso)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("es"))
            fecha.format(formatter)
        } catch (e: Exception) {
            "Fecha inválida"
        }
    }

    fun formatearFechaHora(fechaIso: String?): String {
        if (fechaIso.isNullOrBlank()) return "Sin fecha"
        return try {
            val fecha = ZonedDateTime.parse(fechaIso)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale("es"))
            fecha.format(formatter)
        } catch (e: Exception) {
            "Fecha inválida"
        }
    }

    fun formatearFecha(localDate: LocalDate?): String {
        if (localDate == null) return "Sin fecha"
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("es"))
            localDate.format(formatter)
        } catch (e: Exception) {
            "Fecha inválida"
        }
    }
}

