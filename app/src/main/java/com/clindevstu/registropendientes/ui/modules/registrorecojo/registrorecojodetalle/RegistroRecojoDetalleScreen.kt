package com.clindevstu.registropendientes.ui.modules.registrorecojo.registrorecojodetalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.clindevstu.registropendientes.core.common.AppDataOptions
import com.clindevstu.registropendientes.ui.components.CComboBox
import com.clindevstu.registropendientes.ui.components.CDatePicker
import com.clindevstu.registropendientes.ui.components.CTextInput
import com.clindevstu.registropendientes.ui.components.CTextInputMultiline
import com.clindevstu.registropendientes.ui.modules.registrorecojo.RegistroRecojoViewModel
import com.clindevstu.registropendientes.ui.modules.registrorecojo.agregarregistrorecojo.AgregarRegistroRecojoState

@Composable
fun ScreenRegistroRecojoDetalle (){
    val viewModel: RegistroRecojoViewModel = hiltViewModel()
    RegistroRecojoDetalleScreen(viewModel)
}
@Composable
fun RegistroRecojoDetalleScreen (viewModel: RegistroRecojoViewModel){

    val scrollState = rememberScrollState()
    val registroDetallado by viewModel.registroDetalle.collectAsState()
    val state by viewModel.stateRegistroDetalle.collectAsState()

    Column(modifier = Modifier.padding(0.dp)) {

        when (state) {
            is RegistroRecojoDetalleState.Init,
            is RegistroRecojoDetalleState.Loading -> {
                // Puedes poner un shimmer o CircularProgressIndicator
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is RegistroRecojoDetalleState.Error -> {
                val message = (state as RegistroRecojoDetalleState.Error).message
                Text(
                    text = "Error: $message",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            is RegistroRecojoDetalleState.Success -> {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.asesor.toString(),
                        label = "Asesor",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )

                    CTextInput(
                        value = registroDetallado?.codigoUsuario.toString(),
                        label = "Código Usuario",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )

                    CTextInput(
                        value = registroDetallado?.empresa.toString(),
                        label = "Empresa",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                CTextInput(
                    value = registroDetallado?.nombreCliente.toString(),
                    label = "Nombre Cliente",
                    onValueChange = {},
                    readOnly = true,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.ciudadZona.toString(),
                        label = "Ciudad/Zona",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.tipoRegistro.toString(),
                        label = "Router/Deco",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.paraElDia.toString(),
                        label = "Dia Recojo",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
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
                        value = registroDetallado?.fechaRecojo.toString(),
                        label = "FechaRecojo",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.celular.toString(),
                        label = "Celulares",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                CTextInputMultiline(
                    value = registroDetallado?.direccion.toString(),
                    label = "Direccion",
                    onValueChange = {},
                    readOnly = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                CTextInputMultiline(
                    value = registroDetallado?.motivo.toString(),
                    label = "Motivo Recojo",
                    onValueChange = {},
                    readOnly = true
                )
            }
        }

    }
}