package com.clindevstu.registropendientes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CButtonIconMenuGrid(
    items: List<Triple<String, Painter, () -> Unit>>,
    columns: Int,
    modifier: Modifier = Modifier
) {
    val rows = (items.size + columns - 1) / columns // Redondea hacia arriba

    Column(modifier = modifier) {
        for (rowIndex in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                for (columnIndex in 0 until columns) {
                    val itemIndex = rowIndex * columns + columnIndex
                    if (itemIndex < items.size) {
                        val (text, icon, onClick) = items[itemIndex]
                        CButtonIconMenu(
                            text = text,
                            icon = icon,
                            onClick = onClick,
                            modifier = Modifier
                                .weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CButtonIconMenuGridPreview() {
    val items = listOf(
        Triple("Inicio", rememberVectorPainter(Icons.Default.Home)) {},
        Triple("Buscar", rememberVectorPainter(Icons.Default.Search)) {},
        Triple("Perfil", rememberVectorPainter(Icons.Default.Person)) {},
        Triple("Ajustes", rememberVectorPainter(Icons.Default.Settings)) {},
        Triple("Salir", rememberVectorPainter(Icons.Default.ExitToApp)) {}
    )

    MaterialTheme (darkColorScheme()) {
        Surface {
            CButtonIconMenuGrid(
                items = items,
                columns = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}
