package com.clindevstu.registropendientes.ui.modules.registrointernet.agregarregistrointernet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import com.clindevstu.registropendientes.ui.modules.registrointernet.RegistroInternetViewModel

@Composable
fun ScreenAgregarRegistroInternet(){
    val viewModel : RegistroInternetViewModel = hiltViewModel()
    AgregarRegistroInternetScreen(viewModel)
}

@Composable
fun AgregarRegistroInternetScreen(viewModel: RegistroInternetViewModel){

    val state by viewModel.stateAgregarRegistro.collectAsState()
    val scrollState = rememberScrollState()

    // Para controlar el diálogo (evitar que reaparezca tras rotación, etc.)
    var showDialog by remember { mutableStateOf(false) }
    val errorMessage = (state as? AgregarRegistroInternetState.Error)?.message.orEmpty()
    val isConfirmSendDialogActive by viewModel.isConfirmSendDialogActive.collectAsState()

    val codigoRegistro by viewModel.codigoRegistro.collectAsState()
    val nombreCliente by viewModel.nombreCliente.collectAsState()
    val dni by viewModel.dni.collectAsState()
    val celular by viewModel.celular.collectAsState()
    val direccion by viewModel.direccion.collectAsState()
    val descripcion by viewModel.descripcion.collectAsState()
    val selectedAsesor by viewModel.selectedAsesor.collectAsState()
    val selectedEmpresa by viewModel.selectedEmpresa.collectAsState()
    val selectedFechaRegistro by viewModel.selectedFechaRegistro.collectAsState()
    val selectedCiudadZona by viewModel.selectedCiudadZona.collectAsState()
    val selectedParaElDia by viewModel.selectedParaElDia.collectAsState()
    val selectedTipoRegistro by viewModel.selectedTipoRegistro.collectAsState()
    val selectedPrioridad by viewModel.selectedPrioridad.collectAsState()
    val plan by viewModel.plan.collectAsState()
    val costoPlan by viewModel.costoPlan.collectAsState()
    val costoInst by viewModel.costoInst.collectAsState()
    val promocion by viewModel.promocion.collectAsState()
    val kitAntena by viewModel.kitAntena.collectAsState()
    val costoKit by viewModel.costoKit.collectAsState()
    val costoAntena by viewModel.costoAntena.collectAsState()
    val dias by viewModel.dias.collectAsState()
    val adelanto by viewModel.adelanto.collectAsState()
    val total by viewModel.total.collectAsState()
    val celular2 by viewModel.celular2.collectAsState()
    val referencia by viewModel.referencia.collectAsState()

    val codigoRegistroError by viewModel.codigoRegistroError.collectAsState()
    val nombreClienteError by viewModel.nombreClienteError.collectAsState()
    val dniError by viewModel.dniError.collectAsState()
    val celularError by viewModel.celularError.collectAsState()
    val direccionError by viewModel.direccionError.collectAsState()
    val notaImportanteError by viewModel.notaImportanteError.collectAsState()
    val selectedAsesorError by viewModel.selectedAsesorError.collectAsState()
    val selectedEmpresaError by viewModel.selectedEmpresaError.collectAsState()
    val selectedFechaRegistroError by viewModel.selectedFechaRegistroError.collectAsState()
    val selectedCiudadZonaError by viewModel.selectedCiudadZonaError.collectAsState()
    val selectedParaElDiaError by viewModel.selectedParaElDiaError.collectAsState()
    val selectedTipoRegistroError by viewModel.selectedTipoRegistroError.collectAsState()
    val selectedPrioridadError by viewModel.selectedPrioridadError.collectAsState()
    val planError by viewModel.planError.collectAsState()
    val costoPlanError by viewModel.costoPlanError.collectAsState()
    val costoInstError by viewModel.costoInstError.collectAsState()
    val promocionError by viewModel.promocionError.collectAsState()
    val kitAntenaError by viewModel.kitAntenaError.collectAsState()
    val costoKitError by viewModel.costoKitError.collectAsState()
    val costoAntenaError by viewModel.costoAntenaError.collectAsState()
    val diasError by viewModel.diasError.collectAsState()
    val adelantoError by viewModel.adelantoError.collectAsState()
    val totalError by viewModel.totalError.collectAsState()
    val celular2Error by viewModel.celular2Error.collectAsState()
    val referenciaError by viewModel.referenciaError.collectAsState()
    val descripcionError by viewModel.descripcionError.collectAsState()


    LaunchedEffect(state) {
        if (state is AgregarRegistroInternetState.Success || state is AgregarRegistroInternetState.Error) {
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
                options = AppDataOptions.tipoInstalacion,
                label = "Tipo",
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
                selectedDate = selectedFechaRegistro.toString(),
                onDateSelected = viewModel::onSelectedFechaRegistroChange,
                error = selectedFechaRegistroError,
                modifier = Modifier.weight(1f)
            )
            CComboBox(
                options = AppDataOptions.paraElDia,
                label = "Dia",
                selectedOption = selectedParaElDia.orEmpty(),
                onOptionSelected = viewModel::onSelectedParaElDiaChange,
                error = selectedParaElDiaError,
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
                value = dni.orEmpty(),
                label = "DNI",
                onValueChange = viewModel::onDniChange,
                error = dniError,
                        modifier = Modifier.weight(1f)
            )
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CTextInput(
                value = plan.orEmpty(),
                label = "Plan",
                onValueChange = viewModel::onPlanChange,
                error = planError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                value = costoPlan.orEmpty(),
                label = "CostoPlan",
                onValueChange = viewModel::onCostoPlanChange,
                error = costoPlanError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                value = costoInst.orEmpty(),
                label = "CostoInst",
                onValueChange = viewModel::onCostoInstChange,
                error = costoInstError,
                modifier = Modifier.weight(1f)
            )
        }

        CTextInput(
            value = promocion.orEmpty(),
            label = "Promcion",
            onValueChange = viewModel::onPromocionChange,
            error = promocionError,
        )

        if (selectedTipoRegistro == "Migración Inalámbrico" || selectedTipoRegistro == "Inalámbrico"){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CComboBox(
                    options = AppDataOptions.kitAntena,
                    label = "Kit",
                    selectedOption = kitAntena.orEmpty(),
                    onOptionSelected = viewModel::onKitAntenaChange,
                    error = kitAntenaError,
                    modifier = Modifier.weight(1f)
                )
                CTextInput(
                    value = costoKit.orEmpty(),
                    label = "CostoKit",
                    onValueChange = viewModel::onCostoKitChange,
                    error = costoKitError,
                    modifier = Modifier.weight(1f)
                )
                CTextInput(
                    value = costoAntena.orEmpty(),
                    label = "Promcion",
                    onValueChange = viewModel::onCostoAntenaChange,
                    error = costoAntenaError,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CTextInput(
                value = dias.orEmpty(),
                label = "Dias Rest",
                onValueChange = viewModel::onDiasChange,
                error = diasError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                value = adelanto.orEmpty(),
                label = "Adelanto",
                onValueChange = viewModel::onAdelantoChange,
                error = adelantoError,
                modifier = Modifier.weight(1f)
            )
            CTextInput(
                value = total.orEmpty(),
                label = "Total",
                onValueChange = viewModel::onTotalChange,
                error = totalError,
                modifier = Modifier.weight(1f)
            )
        }
        CComboBox(
            options = AppDataOptions.prioridad,
            label = "Prioridad",
            selectedOption = selectedPrioridad.orEmpty(),
            onOptionSelected = viewModel::onSelectedPrioridadChange,
            error = selectedPrioridadError,
        )
        CTextInputMultiline(
            value = descripcion.orEmpty(),
            label = "Descripcion",
            onValueChange = viewModel::onDescripcionChange,
            error = descripcionError,
        )
        Button(
            onClick = {
                viewModel.onConfirmSendDialogChange(true)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
        Spacer(Modifier.padding(8.dp))
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    viewModel.resetStateAgregarRegistro() // implementa este método en tu VM para ir a Init
                },
                title = {
                    Text(
                        text = if (state is AgregarRegistroInternetState.Success) "Registro guardado" else "Error",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                text = {
                    Text(
                        text = when (state) {
                            is AgregarRegistroInternetState.Success -> "El registro se envió correctamente."
                            is AgregarRegistroInternetState.Error   -> errorMessage
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