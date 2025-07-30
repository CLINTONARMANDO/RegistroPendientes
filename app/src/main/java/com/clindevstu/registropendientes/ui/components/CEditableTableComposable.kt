package com.clindevstu.registropendientes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CEditableTableComposable(
    headers: List<@Composable () -> Unit>,
    data: List<List<@Composable () -> Unit>>,
    columnWeights: List<Float>,
    modifier: Modifier = Modifier
) {
    require(headers.size == columnWeights.size) {
        "El número de columnas en headers debe coincidir con los pesos de columnWeights"
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp)
        ) {
            headers.forEachIndexed { index, header ->
                Box(
                    modifier = Modifier
                        .weight(columnWeights[index])
                        .padding(horizontal = 4.dp)
                ) {
                    header()
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Celdas
        data.forEach { row ->
            require(row.size == columnWeights.size) {
                "Cada fila debe tener la misma cantidad de columnas que columnWeights"
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            ) {
                row.forEachIndexed { colIndex, cellComposable ->
                    Box(
                        modifier = Modifier
                            .weight(columnWeights[colIndex])
                            .padding(horizontal = 4.dp)
                    ) {
                        cellComposable()
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun EditableTableComposablePreview() {
    val tablaState = remember {
        mutableStateOf(
            listOf(
                listOf("Juan", "Av. Los Andes", "987654321"),
                listOf("Ana", "Jr. Cusco", "912345678"),
                listOf("", "", "")
            )
        )
    }

    val headers: List<@Composable () -> Unit> = listOf(
        { Text("Nombre") },
        { Text("Dirección") },
        { Text("Celular") }
    )

    val columnWeights = listOf(0.3f, 0.5f, 0.2f) // 30% nombre, 50% dirección, 20% celular

    val data: List<List<@Composable () -> Unit>> = tablaState.value.mapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, cellValue ->
            @Composable {
                OutlinedTextField(
                    value = cellValue,
                    onValueChange = { newValue ->
                        tablaState.value = tablaState.value.toMutableList().also { newRows ->
                            newRows[rowIndex] = newRows[rowIndex].toMutableList().also { newCols ->
                                newCols[colIndex] = newValue
                            }
                        }
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }


    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            CEditableTableComposable(
                headers = headers,
                data = data,
                columnWeights = columnWeights
            )
        }
    }
}


