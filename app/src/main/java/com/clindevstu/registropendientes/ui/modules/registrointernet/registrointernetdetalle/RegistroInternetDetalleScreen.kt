package com.clindevstu.registropendientes.ui.modules.registrointernet.registrointernetdetalle

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
import com.clindevstu.registropendientes.ui.modules.registrointernet.RegistroInternetViewModel

@Composable
fun ScreenRegistroInternetDetalle (){
    val viewModel: RegistroInternetViewModel = hiltViewModel()
    RegistroInternetDetalleScreen(viewModel)
}

@Composable
fun RegistroInternetDetalleScreen (viewModel: RegistroInternetViewModel){
    val registroDetallado by viewModel.registroDetalle.collectAsState()
    val state by viewModel.stateRegistroDetalle.collectAsState()
    Column(modifier = Modifier.padding(0.dp)) {
        when (state) {
            is RegistroInternetDetalleState.Init,
            is RegistroInternetDetalleState.Loading -> {
                // Puedes poner un shimmer o CircularProgressIndicator
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is RegistroInternetDetalleState.Error -> {
                val message = (state as RegistroInternetDetalleState.Error).message
                Text(
                    text = "Error: $message",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
            is RegistroInternetDetalleState.Success -> {
                CTextInput(
                    value = registroDetallado?.codigoGrande.toString(),
                    label = "Codigo Abrebiado",
                    onValueChange = {},
                    readOnly = true,
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
                        value = registroDetallado?.tipo.toString(),
                        label = "Tipo",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )

                    CTextInput(
                        value = registroDetallado?.lugar.toString(),
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
                        value = registroDetallado?.diaInstalacion.toString(),
                        label = "Dia",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.asesorVentas.toString(),
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
                        value = registroDetallado?.dni.toString(),
                        label = "DNI",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.celulares.toString(),
                        label = "Celulares",
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.plan.toString(),
                        label = "Plan",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.costoPlan.toString(),
                        label = "CostoPlan",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    CTextInput(
                        value = registroDetallado?.costoInst.toString(),
                        label = "CostoInst",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }

                CTextInput(
                    value = registroDetallado?.promocion.toString(),
                    label = "Promcion",
                    onValueChange = {},
                    readOnly = true,
                )

                if (registroDetallado?.tipo.toString() == "Migración Inalámbrico" || registroDetallado?.tipo.toString()  == "Inalámbrico"){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CTextInput(
                            value = registroDetallado?.kitAntena.toString(),
                            label = "Kit",
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.weight(1f)
                        )
                        CTextInput(
                            value = registroDetallado?.costoKit.toString(),
                            label = "CostoKit",
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.weight(1f)
                        )
                        CTextInput(
                            value = registroDetallado?.promocion.toString(),
                            label = "Promcion",
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CTextInput(
                        value = registroDetallado?.dias.toString(),
                        label = "Dias Rest",
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
                        value = registroDetallado?.total.toString(),
                        label = "Total",
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                }
                CTextInput(
                    value = registroDetallado?.prioridad.toString(),
                    label = "Prioridad",
                    onValueChange = {},
                    readOnly = true,
                )
                CTextInputMultiline(
                    value = registroDetallado?.otrosDetalles.toString(),
                    label = "Detalles",
                    onValueChange = {},
                    readOnly = true,
                )
            }
        }
    }
}