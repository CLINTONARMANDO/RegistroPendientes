package com.clindevstu.registropendientes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CDataGridPrueba(
    headers: List<String>,
    data: List<String>,
    colums: Int,
    modifier: Modifier = Modifier
) {
    val pairs = headers.zip(data) // Une encabezado con dato
    Row(modifier = modifier) {
        pairs.forEach { (header, value) ->
                // Celda de encabezado
            Column(modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = header
                )
                Text(
                    text = value
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCDataGridPrueba() {
    val headers = listOf("Hora", "Día", "Asesor")
    val data = listOf("8:30", "Lunes", "José")

    MaterialTheme {
        CDataGridPrueba(
            headers = headers,
            data = data,
            colums = 3,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}
