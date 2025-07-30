package com.clindevstu.registropendientes.ui.modules.registroaverias.agregarregistroaverias

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.clindevstu.registropendientes.core.common.AppDataOptions
import com.clindevstu.registropendientes.ui.components.CComboBox
import com.clindevstu.registropendientes.ui.components.CDatePicker
import com.clindevstu.registropendientes.ui.components.CTextInput
import com.clindevstu.registropendientes.ui.components.CTextInputMultiline
import com.clindevstu.registropendientes.ui.modules.registroaverias.RegistroAveriasViewModel

@Composable
fun ScreenAgregarRegistroAverias (){
    val viewModel: RegistroAveriasViewModel = hiltViewModel()
    AgregarRegistroAveriasScreen(viewModel)
}
@Composable
fun AgregarRegistroAveriasScreen (viewModel: RegistroAveriasViewModel){

    val state by viewModel.stateAgregarRegistro.collectAsState()
    val scrollState = rememberScrollState()

    // Para controlar el diálogo (evitar que reaparezca tras rotación, etc.)
    var showDialog by remember { mutableStateOf(false) }
    val errorMessage = (state as? AgregarRegistroAveriasState.Error)?.message.orEmpty()


    val codigoRegistro     by viewModel.codigoRegistro.collectAsState()
    val nombreCliente      by viewModel.nombreCliente.collectAsState()
    val codigoUsuario      by viewModel.codigoUsuario.collectAsState()
    val direccion          by viewModel.direccion.collectAsState()
    val celular            by viewModel.celular.collectAsState()
    val descripcion        by viewModel.descripcion.collectAsState()
    val selectedAsesor     by viewModel.selectedAsesor.collectAsState()
    val selectedEmpresa    by viewModel.selectedEmpresa.collectAsState()
    val selectedCiudadZona by viewModel.selectedCiudadZona.collectAsState()
    val selectedFechaRegistro by viewModel.selectedFechaRegistro.collectAsState()
    val selectedPrioridad  by viewModel.selectedPrioridad.collectAsState()
    val selectedTipoRegistro by viewModel.selectedTipoRegistro.collectAsState()
    val selectedParaElDia  by viewModel.selectedParaElDia.collectAsState()
    val celular2           by viewModel.celular2.collectAsState()
    val referencia         by viewModel.referencia.collectAsState()

    val codigoRegistroError     by viewModel.codigoRegistroError.collectAsState()
    val nombreClienteError      by viewModel.nombreClienteError.collectAsState()
    val codigoUsuarioError      by viewModel.codigoUsuarioError.collectAsState()
    val direccionError          by viewModel.direccionError.collectAsState()
    val celularError            by viewModel.celularError.collectAsState()
    val descripcionError        by viewModel.descripcionError.collectAsState()
    val selectedAsesorError     by viewModel.selectedAsesorError.collectAsState()
    val selectedEmpresaError    by viewModel.selectedEmpresaError.collectAsState()
    val selectedCiudadZonaError by viewModel.selectedCiudadZonaError.collectAsState()
    val selectedFechaRegistroError by viewModel.selectedFechaRegistroError.collectAsState()
    val selectedPrioridadError  by viewModel.selectedPrioridadError.collectAsState()
    val selectedTipoRegistroError by viewModel.selectedTipoRegistroError.collectAsState()
    val selectedParaElDiaError  by viewModel.selectedParaElDiaError.collectAsState()
    val celular2Error           by viewModel.celular2Error.collectAsState()
    val referenciaError         by viewModel.referenciaError.collectAsState()



    LaunchedEffect(state) {
        if (state is AgregarRegistroAveriasState.Success || state is AgregarRegistroAveriasState.Error) {
            showDialog = true
        }
    }

    Column(modifier = Modifier.padding(0.dp)) {

        CTextInput(
            value = codigoRegistro.orEmpty(),
            label = "Codigo Abrebiado",
            onValueChange = viewModel::onCodigoRegistroChange,
            error = codigoRegistroError
        )

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
                options = AppDataOptions.tipoAveria,
                label = "Tipo",
                selectedOption = selectedTipoRegistro.orEmpty(),
                onOptionSelected = viewModel::onSelectedTipoRegistroChange,
                error = selectedTipoRegistroError,
                modifier = Modifier.weight(1f)
            )

            CTextInput(
                value = selectedCiudadZona.orEmpty(),
                label = "Ciudad",
                onValueChange = viewModel::onSelectedCiudadZonaChange,
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
                selectedDate = selectedFechaRegistro.toString(),
                onDateSelected = viewModel::onSelectedFechaRegistroChange,
                error = selectedFechaRegistroError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                value = codigoUsuario.orEmpty(),
                label = "CodUsuario",
                onValueChange = viewModel::onCodigoUsuarioChange,
                error = codigoUsuarioError,
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
                value = celular.orEmpty(),
                label = "Celular",
                onValueChange = viewModel::onCelularChange,
                error = celularError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                value = celular2.orEmpty(),
                label = "Celular2",
                onValueChange = viewModel::onCelular2Change,
                error = celular2Error,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.prioridad,
                label = "Prioridad",
                selectedOption = selectedPrioridad.orEmpty(),
                onOptionSelected = viewModel::onSelectedPrioridadChange,
                error = selectedPrioridadError,
                modifier = Modifier.weight(1f)
            )
        }

        CTextInput(
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

        CTextInput(
            value = descripcion.orEmpty(),
            label = "Descripcion",
            onValueChange = viewModel::onDescripcionChange,
            error = descripcionError
        )

        Button(
            onClick = {
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
                    viewModel.resetStateAgregarRegistro()
                },
                title = {
                    Text(
                        text = if (state is AgregarRegistroAveriasState.Success) "Registro guardado" else "Error",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                text = {
                    Text(
                        text = when (state) {
                            is AgregarRegistroAveriasState.Success -> "El registro se envió correctamente."
                            is AgregarRegistroAveriasState.Error   -> errorMessage
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