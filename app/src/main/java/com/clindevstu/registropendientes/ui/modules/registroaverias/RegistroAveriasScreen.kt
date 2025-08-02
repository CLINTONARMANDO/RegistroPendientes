package com.clindevstu.registropendientes.ui.modules.registroaverias

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.clindevstu.registropendientes.R
import com.clindevstu.registropendientes.common.AppFunctions
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.ui.components.CCardGridData
import com.clindevstu.registropendientes.ui.components.CFullScreenModal
import com.clindevstu.registropendientes.ui.components.CTextDialog
import com.clindevstu.registropendientes.ui.components.CTopAppBarBody
import com.clindevstu.registropendientes.ui.components.CTopBarSecondary
import com.clindevstu.registropendientes.ui.modules.registroaverias.agregarregistroaverias.AgregarRegistroAveriasScreen
import com.clindevstu.registropendientes.ui.modules.registroaverias.registroaveriasdetalle.RegistroAveriasDetalleScreen
import com.clindevstu.registropendientes.ui.modules.registrointernet.RegistroInternetState

@Composable
fun ScreenRegistroAverias (navController: NavHostController){
    val viewModel: RegistroAveriasViewModel = hiltViewModel()
    RegistroAveriasScreen(navController, viewModel)
}

@Composable
fun RegistroAveriasScreen (navController: NavHostController, viewModel: RegistroAveriasViewModel){

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val usuario by userPreferences.userData.collectAsState(initial = UsuarioResponse())
    val isAgregarDialogActive by viewModel.isAgregarDialogActive.collectAsState()
    val isDetalleDialogActive by viewModel.isDetalleDialogActive.collectAsState()
    val isConfirmCloseDialogActive by viewModel.isConfirmCloseDialogActive.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerContexto(context)
        viewModel.obtenerRegistros()
    }

    Scaffold(
        topBar = {
            CTopBarSecondary(
                title = "Registro Averias",
                subtitle = "Registro de Averias",
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            CTopAppBarBody(
                title = "Mis Averias",
                actions = listOf(
                    Icons.Default.Search to { println("Search clicked") },
                    Icons.Default.AddCircle to { viewModel.onAgregarDialogChange(true) }
                )
            )

            when (state) {
                is RegistroAveriasState.Init,
                is RegistroAveriasState.Loading -> {
                    // Puedes poner un shimmer o CircularProgressIndicator
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                is RegistroAveriasState.Error -> {
                    val message = (state as RegistroAveriasState.Error).message
                    Text(
                        text = "Error: $message",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is RegistroAveriasState.Success -> {
                    val listaRegistros = viewModel.listaRegistros.collectAsState().value
                    if (listaRegistros.isEmpty()) {
                        Text(
                            text = "No se encontraron registros.",
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        listaRegistros.forEach { registro ->
                            val opcionesRegistros = listOf(
                                "Ver Detalle" to {
                                    viewModel.obtenerRegistroDetalle(registro.id ?: "")
                                    viewModel.onDetalleDialogChange(true)
                                },
                                "Editar" to { /* lógica para editar */ },
                                "Eliminar" to { /* lógica para eliminar */ }
                            )
                            CCardGridData(
                                principalIcon = painterResource(id = R.drawable.registro_averias_icon),
                                title = registro.codigoRegistro ?: "Sin Cliente",
                                subTitle = registro.paraElDia ?: "Sin Dirección",
                                thumbnailIcon = painterResource(R.drawable.menu_24px),
                                estado = registro.estado ?: "Desconocido",
                                headers = listOf("Asesor", "Empresa", "Ciudad", "Tipo", "Prioridad", "FechaRegistro"),
                                data = listOf(
                                    registro.asesor ?: "N/A",
                                    registro.empresa ?: "N/A",
                                    registro.ciudadZona ?: "N/A",
                                    registro.tipoRegistro ?: "N/A",
                                    registro.prioridad ?: "N/A",
                                    AppFunctions.formatearFecha(registro.fechaRegistro)
                                ),
                                columns = 3,
                                actions = opcionesRegistros
                            )
                        }
                    }
                }
            }
        }
    }

    if (isAgregarDialogActive) {
        CFullScreenModal(
            title = "Agregar Registro",
            onClose = { viewModel.onConfirmCloseDialogChange(true) }
        ) {
            AgregarRegistroAveriasScreen(viewModel)
        }
    }

    if (isConfirmCloseDialogActive) {
        CTextDialog(
            onDismiss = { viewModel.onConfirmCloseDialogChange(false) },
            text = "¿Estás seguro de cerrar sin guardar?",
            icon = Icons.Default.Warning,
            onAccept = {
                viewModel.onAgregarDialogChange(false)
                viewModel.onConfirmCloseDialogChange(false)
            }
        )
    }

    if (isDetalleDialogActive) {
        CFullScreenModal(
            title = "Detalle del Registro",
            onClose = { viewModel.onDetalleDialogChange(false) }
        ) {
            RegistroAveriasDetalleScreen(viewModel) // Aquí el detalle real
        }
    }
}