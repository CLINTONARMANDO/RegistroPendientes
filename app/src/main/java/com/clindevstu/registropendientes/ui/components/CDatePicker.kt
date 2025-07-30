package com.clindevstu.registropendientes.ui.components

import android.os.Build
import androidx.compose.material3.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CDatePicker(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null,
    enabled: Boolean = true,
    customLectura: Boolean = true
) {
    var openDialog by remember { mutableStateOf(false) }
    val isError = error != null

    // Control de apertura sólo si está “habilitado para lectura”
    val canOpen = enabled && customLectura

    Column(modifier = modifier.padding(bottom = 0.dp)) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { /*no editable*/ },
            readOnly = true,
            // Lo deshabilitamos para que no capture el clic internamente
            enabled = false,
            isError = isError,
            textStyle = MaterialTheme.typography.bodyMedium,     // Texto del campo
            label = {
                Text(
                    text = label,
                    maxLines = 1
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                // Anulamos los estilos “disabled” para que no parezca grisado
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier
                .padding(bottom = 0.dp, top = 0.dp)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .clickable(enabled = canOpen) {
                    openDialog = true
                }
                // (Opcional) degradado o fondo como tú ya lo tenías
                .background(
                    brush = if (!enabled && customLectura) {
                        Brush.verticalGradient(listOf(Color.Transparent, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)))
                    } else Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent))
                )
        )

        if (!error.isNullOrBlank()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 2.dp)
            )
        }
    }

    if (openDialog) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate.takeIf { it.isNotBlank() }?.let {
                runCatching {
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it)?.time
                }.getOrNull()
            }
        )
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        onDateSelected(fmt.format(Date(millis)))
                    }
                    openDialog = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}



