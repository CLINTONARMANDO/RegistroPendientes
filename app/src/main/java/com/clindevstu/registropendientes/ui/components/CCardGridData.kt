package com.clindevstu.registropendientes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clindevstu.registropendientes.R

@Composable
fun CCardGridData(
    principalIcon: Painter,
    title: String,
    subTitle: String,
    thumbnailIcon: Painter,
    estado: String,
    headers: List<String>,
    data: List<String>,
    columns: Int,
    modifier: Modifier = Modifier,
    actions: List<Pair<String, () -> Unit>> = emptyList()
) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ícono principal
                Image(
                    painter = principalIcon,
                    contentDescription = "Ícono principal",
                    modifier = Modifier.size(72.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Título y subtítulo
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Divider()
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Menú desplegable a la derecha
                Column(horizontalAlignment = Alignment.End) {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Image(
                                painter = thumbnailIcon,
                                contentDescription = "Opciones",
                                modifier = Modifier.size(32.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            actions.forEach { (label, action) ->
                                DropdownMenuItem(
                                    text = { Text(label) },
                                    onClick = {
                                        expanded = false
                                        action()
                                    }
                                )
                            }
                        }
                    }

                    Text(
                        text = estado,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            CGridData(
                headers = headers,
                data = data,
                columns = columns
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CCardGridDataPreview() {
    val headers = listOf("Nombre", "Edad", "Ciudad","Nombre", "Edad", "Ciudad")
    val data = listOf("Ana", "25", "Lima")

    CCardGridData(
        principalIcon = painterResource(R.drawable.registro_internet_icon),
        title = "Usuario Destacado",
        subTitle = "Resumen mensual",
        thumbnailIcon = painterResource(R.drawable.registro_internet_icon),
        estado = "Activo",
        headers = headers,
        data = data,
        columns = 3
    )
}