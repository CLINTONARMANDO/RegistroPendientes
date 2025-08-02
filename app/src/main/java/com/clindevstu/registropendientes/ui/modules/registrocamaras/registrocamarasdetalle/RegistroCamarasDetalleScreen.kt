package com.clindevstu.registropendientes.ui.modules.registrocamaras.registrocamarasdetalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.clindevstu.registropendientes.common.AppDataOptions
import com.clindevstu.registropendientes.ui.components.CComboBox
import com.clindevstu.registropendientes.ui.components.CDatePicker
import com.clindevstu.registropendientes.ui.components.CEditableTableComposable
import com.clindevstu.registropendientes.ui.components.CTextInput
import com.clindevstu.registropendientes.ui.components.CTextInputMultiline
import com.clindevstu.registropendientes.ui.modules.registrocamaras.RegistroCamarasViewModel
import com.clindevstu.registropendientes.ui.modules.registrocamaras.agregarregistrocamaras.FilaProducto
import com.clindevstu.registropendientes.ui.modules.registrointernet.registrointernetdetalle.RegistroInternetDetalleScreen

@Composable
fun ScreenRegistroCamarasDetalle (){
    val viewModel: RegistroCamarasViewModel = hiltViewModel()
    RegistroCamarasDetalleScreen(viewModel)
}

@Composable
fun RegistroCamarasDetalleScreen (
    viewModel: RegistroCamarasViewModel
){
    val registroDetallado by viewModel.registroDetalle.collectAsState()
    val state by viewModel.stateRegistroDetalle.collectAsState()
    Column(modifier = Modifier.padding(0.dp)) {
        when (state) {
            is RegistroCamarasDetalleState.Init,
            is RegistroCamarasDetalleState.Loading -> {
                // Puedes poner un shimmer o CircularProgressIndicator
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is RegistroCamarasDetalleState.Error -> {
                val message = (state as RegistroCamarasDetalleState.Error).message
                Text(
                    text = "Error: $message",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            is RegistroCamarasDetalleState.Success -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.empresa.toString(),
                        label = "Empresa",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.tipo_registro.toString(),
                        label = "Realizar",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.ciudad_zona.toString(),
                        label = "Lugar",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.fecha_registro.toString(),
                        label = "FechaRegistro",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.para_el_dia.toString(),
                        label = "Para el Dia",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.asesor.toString(),
                        label = "Asesor",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                CTextInput(
                    value = registroDetallado?.nombre_cliente.toString(),
                    label = "Nombre Cliente",
                    onValueChange = {},
                    readOnly = true,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.dni.toString(),
                        label = "DNI",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.celular.toString(),
                        label = "Celular",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(2f)
                    )
                }
                CTextInputMultiline(
                    value = registroDetallado?.direccion.toString(),
                    label = "Direccion",
                    onValueChange = {},
                    readOnly = true,
                )
                CTextInputMultiline(
                    value = registroDetallado?.productos.toString(),
                    label = "Productos",
                    onValueChange = {},
                    readOnly = true,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.total.toString(),
                        label = "Total",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.adelanto.toString(),
                        label = "Adelanto",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.saldo.toString(),
                        label = "Saldo",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}