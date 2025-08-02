package com.clindevstu.registropendientes.ui.modules.registrorecojo.agregarregistrorecojo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.clindevstu.registropendientes.common.AppDataOptions
import com.clindevstu.registropendientes.ui.components.CComboBox
import com.clindevstu.registropendientes.ui.components.CDatePicker
import com.clindevstu.registropendientes.ui.components.CTextDialog
import com.clindevstu.registropendientes.ui.components.CTextInput
import com.clindevstu.registropendientes.ui.components.CTextInputMultiline
import com.clindevstu.registropendientes.ui.modules.registrorecojo.RegistroRecojoViewModel

@Composable
fun ScreenAgregarRegistroRecojo(){
    val viewModel : RegistroRecojoViewModel = hiltViewModel()
    AgregarRegistroRecojoScreen(viewModel)
}

@Composable
fun AgregarRegistroRecojoScreen(viewModel: RegistroRecojoViewModel){

    val state by viewModel.stateAgregarRegistro.collectAsState()
    val scrollState = rememberScrollState()

    // Para controlar el diálogo (evitar que reaparezca tras rotación, etc.)
    var showDialog by remember { mutableStateOf(false) }
    val errorMessage = (state as? AgregarRegistroRecojoState.Error)?.message.orEmpty()
    val isConfirmSendDialogActive by viewModel.isConfirmSendDialogActive.collectAsState()

    val codigoUsuario by viewModel.codigoUsuario.collectAsState()
    val nombreCliente by viewModel.nombreCliente.collectAsState()
    val celular by viewModel.celular.collectAsState()
    val celular2 by viewModel.celular2.collectAsState()
    val referencia by viewModel.referencia.collectAsState()
    val direccion by viewModel.direccion.collectAsState()
    val motivo by viewModel.motivo.collectAsState()
    val selectedAsesor by viewModel.selectedAsesor.collectAsState()
    val selectedCiudadZona by viewModel.selectedCiudadZona.collectAsState()
    val selectedEmpresa by viewModel.selectedEmpresa.collectAsState()
    val selectedTipoRegistro by viewModel.selectedTipoRegistro.collectAsState()
    val selectedFechaRegistro by viewModel.selectedFechaRegistro.collectAsState()
    val selectedFechaRecojo by viewModel.selectedFechaRecojo.collectAsState()
    val selectedParaElDia by viewModel.selectedParaElDia.collectAsState()
    val codigoUsuarioError by viewModel.codigoUsuarioError.collectAsState()
    val nombreClienteError by viewModel.nombreClienteError.collectAsState()
    val celularError by viewModel.celularError.collectAsState()
    val direccionError by viewModel.direccionError.collectAsState()
    val motivoError by viewModel.motivoError.collectAsState()
    val asesorError by viewModel.asesorError.collectAsState()
    val ciudadZonaError by viewModel.ciudadZonaError.collectAsState()
    val empresaError by viewModel.empresaError.collectAsState()
    val tipoRegistroError by viewModel.tipoRegistroError.collectAsState()
    val fechaRegistroError by viewModel.fechaRegistroError.collectAsState()
    val fechaRecojoError by viewModel.fechaRecojoError.collectAsState()
    val paraElDiaError by viewModel.paraElDiaError.collectAsState()
    val celular2Error by viewModel.celular2Error.collectAsState()
    val referenciaError by viewModel.referenciaError.collectAsState()

    LaunchedEffect(state) {
        if (state is AgregarRegistroRecojoState.Success || state is AgregarRegistroRecojoState.Error) {
            showDialog = true
        }
    }

    Column(modifier = Modifier.padding(0.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CComboBox(
                options = AppDataOptions.asesores,
                label = "Asesor",
                selectedOption = selectedAsesor.orEmpty(),
                onOptionSelected = viewModel::onAsesorChange,
                error = asesorError,
                modifier = Modifier.weight(1f)
            )

            CTextInput(
                value = codigoUsuario.orEmpty(),
                label = "Código Usuario",
                onValueChange = viewModel::onCodigoUsuarioChange,
                error = codigoUsuarioError,
                modifier = Modifier.weight(1f)
            )

            CComboBox(
                options = AppDataOptions.empresas,
                label = "Empresa",
                selectedOption = selectedEmpresa.orEmpty(),
                onOptionSelected = viewModel::onEmpresaChange,
                error = empresaError,
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
            CComboBox(
                options = AppDataOptions.ciudadZona,
                label = "Ciudad/Zona",
                selectedOption = selectedCiudadZona.orEmpty(),
                onOptionSelected = viewModel::onCiudadZonaChange,
                error = ciudadZonaError,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.tipoRecojo,
                label = "Router/Deco",
                selectedOption = selectedTipoRegistro.orEmpty(),
                onOptionSelected = viewModel::onTipoRegistroChange,
                error = tipoRegistroError,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.paraElDia,
                label = "Dia Recojo",
                selectedOption = selectedParaElDia.orEmpty(),
                onOptionSelected = viewModel::onParaElDiaChange,
                error = paraElDiaError,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CDatePicker(
                label = "FechaRegistro",
                selectedDate = selectedFechaRegistro.orEmpty(),
                onDateSelected = viewModel::onFechaRegistroChange,
                error = fechaRegistroError,
                modifier = Modifier.weight(1f)
            )
            CDatePicker(
                label = "FechaRecojo",
                selectedDate = selectedFechaRecojo.orEmpty(),
                onDateSelected = viewModel::onFechaRecojoChange,
                error = fechaRecojoError,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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

        CTextInputMultiline(
            value = direccion.orEmpty(),
            label = "Direccion",
            onValueChange = viewModel::onDireccionChange,
            error = direccionError
        )

        CTextInputMultiline(
            value = referencia.orEmpty(),
            label = "Referencia",
            onValueChange = viewModel::onReferenciaChange,
            error = referenciaError
        )

        CTextInputMultiline(
            value = motivo.orEmpty(),
            label = "Motivo Recojo",
            onValueChange = viewModel::onMotivoChange,
            error = motivoError
        )

        Button(
            onClick = {
                viewModel.onConfirmSendDialogChange(true)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    viewModel.resetStateAgregarRegistro() // implementa este método en tu VM para ir a Init
                },
                title = {
                    Text(
                        text = if (state is AgregarRegistroRecojoState.Success) "Registro guardado" else "Error",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                text = {
                    Text(
                        text = when (state) {
                            is AgregarRegistroRecojoState.Success -> "El registro se envió correctamente."
                            is AgregarRegistroRecojoState.Error   -> errorMessage
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
    if (isConfirmSendDialogActive) {
        CTextDialog(
            onDismiss = { viewModel.onConfirmSendDialogChange(false) },
            text = "¿Deseas registrar este nuevo reporte?",
            icon = Icons.Default.CheckCircle,
            onAccept = {
                viewModel.crearNuevoRegistro()
                viewModel.onConfirmSendDialogChange(false)
            }
        )
    }
}