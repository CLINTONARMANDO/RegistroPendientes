package com.clindevstu.registropendientes.ui.modules.registrocamaras.agregarregistrocamaras

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.clindevstu.registropendientes.core.common.AppDataOptions
import com.clindevstu.registropendientes.ui.components.CComboBox
import com.clindevstu.registropendientes.ui.components.CDatePicker
import com.clindevstu.registropendientes.ui.components.CTextInput
import com.clindevstu.registropendientes.ui.components.CEditableTableComposable
import com.clindevstu.registropendientes.ui.components.CTextInputSimple
import com.clindevstu.registropendientes.ui.modules.registrocamaras.RegistroCamarasViewModel
@Composable
fun AgregarRegistroCamarasScreen(viewModel: RegistroCamarasViewModel){

    val state by viewModel.stateAgregarRegistro.collectAsState()
    val scrollState = rememberScrollState()

    // Para controlar el diálogo (evitar que reaparezca tras rotación, etc.)
    var showDialog by remember { mutableStateOf(false) }
    val errorMessage = (state as? AgregarRegistroCamarasState.Error)?.message.orEmpty()

    val nombreCliente by viewModel.nombreCliente.collectAsState()
    val dni by viewModel.dni.collectAsState()
    val celular by viewModel.celular.collectAsState()
    val direccion by viewModel.direccion.collectAsState()
    val productos by viewModel.productos.collectAsState()
    val total by viewModel.total.collectAsState()
    val adelanto by viewModel.adelanto.collectAsState()
    val saldo by viewModel.saldo.collectAsState()
    val selectedEmpresa by viewModel.selectedEmpresa.collectAsState()
    val selectedTipoRegistro by viewModel.selectedTipoRegistro.collectAsState()
    val selectedCiudadZona by viewModel.selectedCiudadZona.collectAsState()
    val fechaRegistro by viewModel.fechaRegistro.collectAsState()
    val paraElDia by viewModel.paraElDia.collectAsState()
    val selectedAsesor by viewModel.selectedAsesor.collectAsState()
    val celular2 by viewModel.celular2.collectAsState()
    val referencia by viewModel.referencia.collectAsState()

    val nombreClienteError by viewModel.nombreClienteError.collectAsState()
    val dniError by viewModel.dniError.collectAsState()
    val celularError by viewModel.celularError.collectAsState()
    val direccionError by viewModel.direccionError.collectAsState()
    val productosError by viewModel.productosError.collectAsState()
    val totalError by viewModel.totalError.collectAsState()
    val adelantoError by viewModel.adelantoError.collectAsState()
    val saldoError by viewModel.saldoError.collectAsState()
    val selectedEmpresaError by viewModel.selectedEmpresaError.collectAsState()
    val selectedTipoRegistroError by viewModel.selectedTipoRegistroError.collectAsState()
    val selectedCiudadZonaError by viewModel.selectedCiudadZonaError.collectAsState()
    val fechaRegistroError by viewModel.fechaRegistroError.collectAsState()
    val paraElDiaError by viewModel.paraElDiaError.collectAsState()
    val selectedAsesorError by viewModel.selectedAsesorError.collectAsState()
    val celular2Error by viewModel.celular2Error.collectAsState()
    val referenciaError by viewModel.referenciaError.collectAsState()


    val filas = remember {
        mutableStateListOf(
            FilaProducto("", "", "", ""),
        )
    }

    val headers = listOf<@Composable () -> Unit>(
        { Text("Detalle") },
        { Text("Cantidad") },
        { Text("Precio") },
        { Text("Total") },
        { Text("Acción") }
    )
    val columnWeights = listOf(6f, 3f, 3f, 3f, 2f)

    val data = filas.mapIndexed { index, fila ->
        listOf<@Composable () -> Unit>(
            {
                CTextInputSimple(
                    value = fila.detalle,
                    onValueChange = { filas[index] = fila.copy(detalle = it) },
                    keyboardType = KeyboardType.Text
                )
            },
            {
                CTextInputSimple(
                    value = fila.cantidad,
                    onValueChange = {
                        val newCantidad = it
                        val cantidadDouble = newCantidad.toDoubleOrNull() ?: 0.0
                        val precioDouble = fila.precio.toDoubleOrNull() ?: 0.0
                        val totalaux = (cantidadDouble * precioDouble).toString()
                        filas[index] = fila.copy(cantidad = newCantidad, total = totalaux)
                    },
                    keyboardType = KeyboardType.Number
                )
            },
            {
                CTextInputSimple(
                    value = fila.precio,
                    onValueChange = {
                        val newPrecio = it
                        val cantidadDouble = fila.cantidad.toDoubleOrNull() ?: 0.0
                        val precioDouble = newPrecio.toDoubleOrNull() ?: 0.0
                        val totalaux = (cantidadDouble * precioDouble).toString()
                        filas[index] = fila.copy(precio = newPrecio, total = totalaux)


                    },
                    keyboardType = KeyboardType.Number
                )
            },
            {
                CTextInputSimple(
                    value = fila.total,
                    onValueChange = {},
                    readOnly = true,
                    keyboardType = KeyboardType.Number
                )
            },
            {
                IconButton(onClick = { filas.removeAt(index) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar fila")
                }
            }
        )
    }




    LaunchedEffect(state) {
        if (state is AgregarRegistroCamarasState.Success || state is AgregarRegistroCamarasState.Error) {
            showDialog = true
        }
    }

    val totalCalculado by remember {
        derivedStateOf {
            filas.sumOf { it.total.toDoubleOrNull() ?: 0.0 }
        }
    }


    LaunchedEffect(totalCalculado) {
        viewModel.onTotalChange("%.2f".format(totalCalculado))
    }

    Column(modifier = Modifier.padding(0.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CComboBox(
                options = AppDataOptions.empresas,
                label = "Empresa",
                selectedOption = selectedEmpresa.orEmpty(),
                onOptionSelected = viewModel::onSelectedEmpresaChange,
                error = selectedEmpresaError,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.tipoRegistroCamara,
                label = "Realizar",
                selectedOption = selectedTipoRegistro.orEmpty(),
                onOptionSelected = viewModel::onSelectedTipoRegistroChange,
                error = selectedTipoRegistroError,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.ciudadZona,
                label = "Lugar",
                selectedOption = selectedCiudadZona.orEmpty(),
                onOptionSelected = viewModel::onSelectedCiudadZonaChange,
                error = selectedCiudadZonaError,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CDatePicker(
                label = "FechaRegistro",
                selectedDate = fechaRegistro.orEmpty(),
                onDateSelected = viewModel::onFechaRegistroChange,
                error = fechaRegistroError,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.paraElDia,
                label = "Para el Dia",
                selectedOption = paraElDia.orEmpty(),
                onOptionSelected = viewModel::onParaElDiaChange,
                error = paraElDiaError,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.asesores,
                label = "Asesor",
                selectedOption = selectedAsesor.orEmpty(),
                onOptionSelected = viewModel::onSelectedAsesorChange,
                error = selectedAsesorError,
                modifier = Modifier.weight(1f)
            )
        }

        CTextInput(
            value = nombreCliente.orEmpty(),
            label = "Nombre Cliente",
            onValueChange = viewModel::onNombreClienteChange,
            error = nombreClienteError
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CTextInput(
                label = "DNI",
                value = dni.orEmpty(),
                onValueChange = viewModel::onDniChange,
                error = dniError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                label = "Celular",
                value = celular.orEmpty(),
                onValueChange = viewModel::onCelularChange,
                error = celularError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                label = "Celular 2",
                value = celular2.orEmpty(),
                onValueChange = viewModel::onCelular2Change,
                error = celular2Error,
                modifier = Modifier.weight(1f)
            )
        }
        CTextInput(
            value = direccion.orEmpty(),
            label = "Direccion",
            onValueChange = viewModel::onDireccionChange,
            error = direccionError
        )
        CTextInput(
            value = referencia.orEmpty(),
            label = "Referencia",
            onValueChange = viewModel::onReferenciaChange,
            error = referenciaError
        )

        CEditableTableComposable(
            headers = headers,
            data = data,
            columnWeights = columnWeights
        )
        Button(
            onClick = {
                filas.add(FilaProducto("", "", "", ""))
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar fila")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Agregar fila")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CTextInput(
                label = "Total",
                value = "%.2f".format(totalCalculado),
                readOnly = true,
                onValueChange = { },
                error = totalError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                label = "Adelanto",
                value = adelanto.orEmpty(),
                onValueChange = {
                    val adelantoSuma = it.toDoubleOrNull() ?: 0.0
                    val saldoCalculado = totalCalculado - adelantoSuma

                    viewModel.onAdelantoChange(it)
                    viewModel.onSaldoChange("%.2f".format(saldoCalculado))
                },
                error = adelantoError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                label = "Saldo",
                value = saldo.orEmpty(),
                readOnly = true,
                onValueChange = { },
                error = saldoError,
                modifier = Modifier.weight(1f)
            )
        }


        Button(
            onClick = {
                viewModel.onProductosChange(
                    construirTextoProductos(filas)
                )
                viewModel.crearNuevoRegistro()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    viewModel.resetStateAgregarRegistro() // implementa este método en tu VM para ir a Init
                },
                title = {
                    Text(
                        text = if (state is AgregarRegistroCamarasState.Success) "Registro guardado" else "Error",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                text = {
                    Text(
                        text = when (state) {
                            is AgregarRegistroCamarasState.Success -> "El registro se envió correctamente."
                            is AgregarRegistroCamarasState.Error   -> errorMessage
                            else                                   -> ""
                        }
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        viewModel.resetStateAgregarRegistro()
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }

}
data class FilaProducto(
    var detalle: String,
    var cantidad: String,
    var precio: String,
    var total: String
)

@Composable
fun ScreenAgregarRegistroCamaras(){
    val viewModel: RegistroCamarasViewModel = hiltViewModel()
    AgregarRegistroCamarasScreen(viewModel)
}

fun construirTextoProductos(filas: List<FilaProducto>): String {
    return filas.joinToString(separator = "\n") { fila ->
        "Detalle: ${fila.detalle}, Cantidad: ${fila.cantidad}, Precio: ${fila.precio}, Total: ${fila.total}:"
    }
}