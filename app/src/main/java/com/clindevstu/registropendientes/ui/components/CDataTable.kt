package com.clindevstu.registropendientes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CDataTable(
    headers: List<String>,
    data: List<String>,
    columns: Int,
    modifier: Modifier = Modifier
) {
    val rows = (data.size + columns - 1) / columns // redondeo hacia arriba
    val totalItems = rows * columns
    val filledData = data + List(totalItems - data.size) { "" }

    Column(modifier = modifier.border(1.dp, Color.Black)) {
        // Encabezados
        Row(Modifier.fillMaxWidth()) {
            headers.take(columns).forEachIndexed { index, header ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .border(
                            BorderStroke(1.dp, Color.Black),
                            RectangleShape
                        )
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(header, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Celdas de datos
        for (row in 0 until rows) {
            Row(Modifier.fillMaxWidth()) {
                for (col in 0 until columns) {
                    val index = row * columns + col
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .border(
                                BorderStroke(1.dp, Color.Black),
                                RectangleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = filledData[index])
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCDataTable() {
    val headers = listOf("Nombre", "Edad", "Ciudad")
    val data = listOf(
        "Ana", "23", "Lima",
        "Luis", "30", "Cusco",
        "Sofía", "27", "Arequipa"
    )
    MaterialTheme {
        CDataTable(
            headers = headers,
            data = data,
            columns = 3,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}
