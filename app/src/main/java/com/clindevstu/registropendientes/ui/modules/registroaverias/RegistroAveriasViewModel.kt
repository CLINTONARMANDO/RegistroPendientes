package com.clindevstu.registropendientes.ui.modules.registroaverias

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.requests.RegistroAveriasRequest
import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroAveriasCompletoResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroAveriasSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroRecojoSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.domain.usecase.RegistroAveriasUseCase
import com.clindevstu.registropendientes.ui.modules.registroaverias.agregarregistroaverias.AgregarRegistroAveriasState
import com.clindevstu.registropendientes.ui.modules.registroaverias.registroaveriasdetalle.RegistroAveriasDetalleState
import com.clindevstu.registropendientes.ui.modules.registrorecojo.RegistroRecojoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroAveriasViewModel @Inject constructor(
    private val useCase: RegistroAveriasUseCase
) : ViewModel(){

    private val _state = MutableStateFlow<RegistroAveriasState>(RegistroAveriasState.Init)
    val state: StateFlow<RegistroAveriasState> = _state

    private var _stateAgregarRegistro = MutableStateFlow<AgregarRegistroAveriasState>(AgregarRegistroAveriasState.Init)
    val stateAgregarRegistro: StateFlow<AgregarRegistroAveriasState> = _stateAgregarRegistro

    private var _stateRegistroDetalle = MutableStateFlow<RegistroAveriasDetalleState>(RegistroAveriasDetalleState.Init)
    val stateRegistroDetalle: StateFlow<RegistroAveriasDetalleState> = _stateRegistroDetalle

    // VARIABLES FORMULARIO

    private val _codigoRegistro = MutableStateFlow<String?>(null)
    val codigoRegistro: StateFlow<String?> = _codigoRegistro

    private val _nombreCliente = MutableStateFlow<String?>(null)
    val nombreCliente: StateFlow<String?> = _nombreCliente

    private val _codigoUsuario = MutableStateFlow<String?>(null)
    val codigoUsuario: StateFlow<String?> = _codigoUsuario

    private val _direccion = MutableStateFlow<String?>(null)
    val direccion: StateFlow<String?> = _direccion

    private val _celular = MutableStateFlow<String?>(null)
    val celular: StateFlow<String?> = _celular

    private val _descripcion = MutableStateFlow<String?>(null)
    val descripcion: StateFlow<String?> = _descripcion

    private val _selectedAsesor = MutableStateFlow<String?>(null)
    val selectedAsesor: StateFlow<String?> = _selectedAsesor

    private val _selectedEmpresa = MutableStateFlow<String?>(null)
    val selectedEmpresa: StateFlow<String?> = _selectedEmpresa

    private val _selectedCiudadZona = MutableStateFlow<String?>(null)
    val selectedCiudadZona: StateFlow<String?> = _selectedCiudadZona

    private val _selectedFechaRegistro = MutableStateFlow<String?>(null)
    val selectedFechaRegistro: StateFlow<String?> = _selectedFechaRegistro

    private val _selectedPrioridad = MutableStateFlow<String?>(null)
    val selectedPrioridad: StateFlow<String?> = _selectedPrioridad

    private val _selectedTipoRegistro = MutableStateFlow<String?>(null)
    val selectedTipoRegistro: StateFlow<String?> = _selectedTipoRegistro

    private val _selectedParaElDia = MutableStateFlow<String?>(null)
    val selectedParaElDia: StateFlow<String?> = _selectedParaElDia

    private val _celular2 = MutableStateFlow<String?>(null)
    val celular2: StateFlow<String?> = _celular2

    private val _referencia = MutableStateFlow<String?>(null)
    val referencia: StateFlow<String?> = _referencia

    // ERRORES

    private val _codigoRegistroError = MutableStateFlow<String?>(null)
    val codigoRegistroError: StateFlow<String?> = _codigoRegistroError

    private val _nombreClienteError = MutableStateFlow<String?>(null)
    val nombreClienteError: StateFlow<String?> = _nombreClienteError

    private val _codigoUsuarioError = MutableStateFlow<String?>(null)
    val codigoUsuarioError: StateFlow<String?> = _codigoUsuarioError

    private val _direccionError = MutableStateFlow<String?>(null)
    val direccionError: StateFlow<String?> = _direccionError

    private val _celularError = MutableStateFlow<String?>(null)
    val celularError: StateFlow<String?> = _celularError

    private val _descripcionError = MutableStateFlow<String?>(null)
    val descripcionError: StateFlow<String?> = _descripcionError

    private val _selectedAsesorError = MutableStateFlow<String?>(null)
    val selectedAsesorError: StateFlow<String?> = _selectedAsesorError

    private val _selectedEmpresaError = MutableStateFlow<String?>(null)
    val selectedEmpresaError: StateFlow<String?> = _selectedEmpresaError

    private val _selectedCiudadZonaError = MutableStateFlow<String?>(null)
    val selectedCiudadZonaError: StateFlow<String?> = _selectedCiudadZonaError

    private val _selectedFechaRegistroError = MutableStateFlow<String?>(null)
    val selectedFechaRegistroError: StateFlow<String?> = _selectedFechaRegistroError

    private val _selectedPrioridadError = MutableStateFlow<String?>(null)
    val selectedPrioridadError: StateFlow<String?> = _selectedPrioridadError

    private val _selectedTipoRegistroError = MutableStateFlow<String?>(null)
    val selectedTipoRegistroError: StateFlow<String?> = _selectedTipoRegistroError

    private val _selectedParaElDiaError = MutableStateFlow<String?>(null)
    val selectedParaElDiaError: StateFlow<String?> = _selectedParaElDiaError

    private val _celular2Error = MutableStateFlow<String?>(null)
    val celular2Error: StateFlow<String?> = _celular2Error

    private val _referenciaError = MutableStateFlow<String?>(null)
    val referenciaError: StateFlow<String?> = _referenciaError

    // VARIABLES REGISTROS

    private val _listaRegistros = MutableStateFlow<List<RegistroAveriasSimpleResponse>>(emptyList())
    val listaRegistros: StateFlow<List<RegistroAveriasSimpleResponse>> = _listaRegistros

    private val _pagina = MutableStateFlow<Int>(1)
    val pagina: StateFlow<Int> = _pagina

    private val _pageSize = MutableStateFlow<Int>(10)
    val pageSize: StateFlow<Int> = _pageSize

    private val _orden = MutableStateFlow<Int>(0)
    val orden: StateFlow<Int> = _orden

    private val _totalPages = MutableStateFlow<Int>(1)
    val totalPages: StateFlow<Int> = _totalPages

    private val _totalItems = MutableStateFlow<Int>(1)
    val totalItems: StateFlow<Int> = _totalItems

    private val _hasNextPage = MutableStateFlow<Boolean>(false)
    val hasNextPage: StateFlow<Boolean> = _hasNextPage

    private val _hasPreviousPage = MutableStateFlow<Boolean>(false)
    val hasPreviousPage: StateFlow<Boolean> = _hasPreviousPage

    private val _registroDetalle = MutableStateFlow<RegistroAveriasCompletoResponse?>(null)
    val registroDetalle: StateFlow<RegistroAveriasCompletoResponse?> = _registroDetalle

    // VARIABLES DE CONTEXTO

    private val _usuario = MutableStateFlow<UsuarioResponse>(UsuarioResponse())
    val usuario: StateFlow<UsuarioResponse> = _usuario.asStateFlow()

    private val _userPreferences = MutableStateFlow<UserPreferences?>(null)
    val userPreferences: StateFlow<UserPreferences?> = _userPreferences.asStateFlow()

    // VARIABLES DE DIALOGS

    private val _isAgregarDialogActive = MutableStateFlow<Boolean>(false)
    val isAgregarDialogActive: StateFlow<Boolean> = _isAgregarDialogActive

    private val _isDetalleDialogActive = MutableStateFlow<Boolean>(false)
    val isDetalleDialogActive: StateFlow<Boolean> = _isDetalleDialogActive

    private val _isConfirmCloseDialogActive = MutableStateFlow<Boolean>(false)
    val isConfirmCloseDialogActive: StateFlow<Boolean> = _isConfirmCloseDialogActive

    private val _isConfirmSendDialogActive = MutableStateFlow<Boolean>(false)
    val isConfirmSendDialogActive: StateFlow<Boolean> = _isConfirmSendDialogActive



    fun obtenerContexto(context: Context){

        _userPreferences.value =  UserPreferences(context)
        viewModelScope.launch {
            _userPreferences.value?.userData?.collect { user ->
                _usuario.value = user
            }
        }
    }

    // FUNCIONES DE OBTENCION DE REGISTROS

    fun obtenerRegistros() {
        viewModelScope.launch {
            val user = _userPreferences.value?.userData?.firstOrNull()
            if (user != null) {
                _usuario.value = user
            }
            _state.value = RegistroAveriasState.Loading

            val resultado: Result<PaginationResponse<RegistroAveriasSimpleResponse>> =
                if (_usuario.value.esTecnico == true) {
                    useCase.obtenerPaginados(_usuario.value.nombreTecnico.orEmpty())
                } else {
                    useCase.obtenerPaginados(_pagina.value, _pageSize.value, _orden.value)
                }

            if (resultado.isSuccess) {
                val paginacion = resultado.getOrNull()
                _listaRegistros.value = paginacion?.data ?: emptyList()
                _totalItems.value = paginacion?.totalItems ?: 0
                _totalPages.value = paginacion?.totalPages ?: 1
                _hasNextPage.value = paginacion?.hasNextPage ?: false
                _hasPreviousPage.value = paginacion?.hasPreviousPage ?: false

                _state.value = RegistroAveriasState.Success
            } else {
                _state.value = RegistroAveriasState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun obtenerRegistroDetalle(id: String) {
        viewModelScope.launch {
            _stateRegistroDetalle.value = RegistroAveriasDetalleState.Loading

            val resultado = useCase.obtenerPorId(id)

            if (resultado.isSuccess) {
                val data = resultado.getOrNull()
                if (data != null) {
                    _registroDetalle.value = data
                    _stateRegistroDetalle.value = RegistroAveriasDetalleState.Success
                } else {
                    _stateRegistroDetalle.value = RegistroAveriasDetalleState.Error("Respuesta vacía del servidor")
                }
            } else {
                _stateRegistroDetalle.value = RegistroAveriasDetalleState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun crearNuevoRegistro() {
        viewModelScope.launch {
            _stateAgregarRegistro.value = AgregarRegistroAveriasState.Loading


            val request = RegistroAveriasRequest(
                codigoGrande  = _codigoRegistro.value,
                asesor        = _selectedAsesor.value,
                empresa       = _selectedEmpresa.value,
                tipo          = _selectedTipoRegistro.value,
                nombreCliente = _nombreCliente.value,
                codigoCliente = _codigoUsuario.value,
                direccion     = _direccion.value,
                referencia    = _referencia.value,   // Falta definir MutableStateFlow<String?> para referencia
                lugar         = _selectedCiudadZona.value,
                celular       = _celular.value,
                celular2      = _celular2.value,    // Falta definir MutableStateFlow<String?> para celular2
                fechaRegistro = _selectedFechaRegistro.value,
                interaccion   = _descripcion.value,
                prioridad     = _selectedPrioridad.value
            )

            val resultado = useCase.crearNuevoRegistro(request)

            if (resultado.isSuccess) {
                val mensaje = resultado.getOrNull()?.mensaje
                _stateAgregarRegistro.value = AgregarRegistroAveriasState.Success
            } else {
                _stateAgregarRegistro.value = AgregarRegistroAveriasState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido al registrar."
                )
            }
        }
    }


    // FUNCIONES DE CAMBIO DE VARIBLES DE FORMULARIO

    fun onCodigoRegistroChange(value: String) {
        _codigoRegistro.value = value
    }

    fun onNombreClienteChange(value: String) {
        _nombreCliente.value = value
    }

    fun onCodigoUsuarioChange(value: String) {
        _codigoUsuario.value = value
    }

    fun onDireccionChange(value: String) {
        _direccion.value = value
    }

    fun onCelularChange(value: String) {
        _celular.value = value
    }

    fun onDescripcionChange(value: String) {
        _descripcion.value = value
    }

    fun onSelectedAsesorChange(value: String) {
        _selectedAsesor.value = value
    }

    fun onSelectedEmpresaChange(value: String) {
        _selectedEmpresa.value = value
    }

    fun onSelectedCiudadZonaChange(value: String) {
        _selectedCiudadZona.value = value
    }

    fun onSelectedFechaRegistroChange(value: String) {
        _selectedFechaRegistro.value = value
    }

    fun onSelectedPrioridadChange(value: String) {
        _selectedPrioridad.value = value
    }

    fun onSelectedTipoRegistroChange(value: String) {
        _selectedTipoRegistro.value = value
    }

    fun onSelectedParaElDiaChange(value: String) {
        _selectedParaElDia.value = value
    }

    fun onCelular2Change(value: String) {
        _celular2.value = value
    }

    fun onReferenciaChange(value: String) {
        _referencia.value = value
    }

    fun onAgregarDialogChange(value: Boolean) {
        _isAgregarDialogActive.value = value
    }

    fun onDetalleDialogChange(value: Boolean) {
        _isDetalleDialogActive.value = value
    }

    fun onConfirmSendDialogChange(value: Boolean) {
        _isConfirmSendDialogActive.value = value
    }

    fun onConfirmCloseDialogChange(value: Boolean) {
        _isConfirmCloseDialogActive.value = value
    }

    // FUNCIONES DE VALIDACION PARA ERRORES

    fun validarCodigoRegistro() {
        _codigoRegistroError.value = if (_codigoRegistro.value.isNullOrBlank()) "El código de registro es obligatorio" else null
    }

    fun validarNombreCliente() {
        _nombreClienteError.value = if (_nombreCliente.value.isNullOrBlank()) "El nombre del cliente es obligatorio" else null
    }

    fun validarCodigoUsuario() {
        _codigoUsuarioError.value = if (_codigoUsuario.value.isNullOrBlank()) "El código de usuario es obligatorio" else null
    }

    fun validarDireccion() {
        _direccionError.value = if (_direccion.value.isNullOrBlank()) "La dirección es obligatoria" else null
    }

    fun validarCelular() {
        _celularError.value = if (_celular.value.isNullOrBlank()) "El celular es obligatorio" else null
    }

    fun validarDescripcion() {
        _descripcionError.value = if (_descripcion.value.isNullOrBlank()) "La descripción es obligatoria" else null
    }

    fun validarSelectedAsesor() {
        _selectedAsesorError.value = if (_selectedAsesor.value.isNullOrBlank()) "Debe seleccionar un asesor" else null
    }

    fun validarSelectedEmpresa() {
        _selectedEmpresaError.value = if (_selectedEmpresa.value.isNullOrBlank()) "Debe seleccionar una empresa" else null
    }

    fun validarSelectedCiudadZona() {
        _selectedCiudadZonaError.value = if (_selectedCiudadZona.value.isNullOrBlank()) "Debe seleccionar una ciudad o zona" else null
    }

    fun validarSelectedFechaRegistro() {
        _selectedFechaRegistroError.value = if (_selectedFechaRegistro.value.isNullOrBlank()) "Debe seleccionar una fecha de registro" else null
    }

    fun validarSelectedPrioridad() {
        _selectedPrioridadError.value = if (_selectedPrioridad.value.isNullOrBlank()) "Debe seleccionar una prioridad" else null
    }

    fun validarSelectedTipoRegistro() {
        _selectedTipoRegistroError.value = if (_selectedTipoRegistro.value.isNullOrBlank()) "Debe seleccionar un tipo de registro" else null
    }

    fun validarSelectedParaElDia() {
        _selectedParaElDiaError.value = if (_selectedParaElDia.value.isNullOrBlank()) "Debe seleccionar un día" else null
    }

    fun validarReferencia() {
        _referenciaError.value = if (_referencia.value.isNullOrBlank()) "La fecha de recojo es obligatoria" else null
    }

    fun validarCelular2() {
        _celular2Error.value = if (_celular2.value.isNullOrBlank()) "Debe indicar si es para el día" else null
    }

    fun resetStateAgregarRegistro() {
        _stateAgregarRegistro.value = AgregarRegistroAveriasState.Init
    }

}