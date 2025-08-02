package com.clindevstu.registropendientes.ui.modules.registroaverias.registroaveriasdetalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.clindevstu.registropendientes.ui.components.CTextInput
import com.clindevstu.registropendientes.ui.components.CTextInputMultiline
import com.clindevstu.registropendientes.ui.modules.registroaverias.RegistroAveriasViewModel

@Composable
fun ScreenRegistroAveriasDetalle (){
    val viewModel: RegistroAveriasViewModel = hiltViewModel()
    RegistroAveriasDetalleScreen(viewModel)
}

@Composable
fun RegistroAveriasDetalleScreen (viewModel: RegistroAveriasViewModel){
    val registroDetallado by viewModel.registroDetalle.collectAsState()
    val state by viewModel.stateRegistroDetalle.collectAsState()
    Column(modifier = Modifier.padding(0.dp)) {
        when (state) {
            is RegistroAveriasDetalleState.Init,
            is RegistroAveriasDetalleState.Loading -> {
                // Puedes poner un shimmer o CircularProgressIndicator
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is RegistroAveriasDetalleState.Error -> {
                val message = (state as RegistroAveriasDetalleState.Error).message
                Text(
                    text = "Error: $message",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            is RegistroAveriasDetalleState.Success -> {
                CTextInput(
                    value = registroDetallado?.codigoRegistro.toString(),
                    label = "Codigo Abrebiado",
                    onValueChange = {},
                    readOnly = true
                )

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
                        value = registroDetallado?.tipoRegistro.toString(),
                        label = "Tipo",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )

                    CTextInput(
                        value = registroDetallado?.ciudadZona.toString(),
                        label = "Ciudad",
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
                        value = registroDetallado?.fechaRegistro.toString(),
                        label = "FechaRegistro",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.codigoUsuario.toString(),
                        label = "CodUsuario",
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
                    value = registroDetallado?.nombreCliente.toString(),
                    label = "Nombre Cliente",
                    onValueChange = {},
                    readOnly = true,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.celular.toString(),
                        label = "Celular",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.celular.toString(),
                        label = "Prioridad",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                CTextInputMultiline(
                    value = registroDetallado?.direccion.toString(),
                    label = "Direccion",
                    onValueChange = {},
                    readOnly = true,
                )

                CTextInputMultiline(
                    value = registroDetallado?.descripcion.toString(),
                    label = "Descripcion",
                    onValueChange = {},
                    readOnly = true,
                )
            }
        }
    }
}