package com.clindevstu.registropendientes.ui.modules.registrocamaras

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.requests.RegistroCamarasRequest
import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasCompletoResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroInternetSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.domain.usecase.RegistroCamarasUseCase
import com.clindevstu.registropendientes.ui.modules.registrocamaras.agregarregistrocamaras.AgregarRegistroCamarasState
import com.clindevstu.registropendientes.ui.modules.registrocamaras.registrocamarasdetalle.RegistroCamarasDetalleState
import com.clindevstu.registropendientes.ui.modules.registrointernet.RegistroInternetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroCamarasViewModel @Inject constructor(
    private val useCase: RegistroCamarasUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<RegistroCamarasState>(RegistroCamarasState.Init)
    val state: StateFlow<RegistroCamarasState> = _state

    private var _stateAgregarRegistro = MutableStateFlow<AgregarRegistroCamarasState>(AgregarRegistroCamarasState.Init)
    val stateAgregarRegistro: StateFlow<AgregarRegistroCamarasState> = _stateAgregarRegistro

    private var _stateRegistroDetalle = MutableStateFlow<RegistroCamarasDetalleState>(RegistroCamarasDetalleState.Init)
    val stateRegistroDetalle: StateFlow<RegistroCamarasDetalleState> = _stateRegistroDetalle

    // VARIABLES FORMULARIO

    private val _nombreCliente = MutableStateFlow<String?>(null)
    val nombreCliente: StateFlow<String?> = _nombreCliente

    private val _dni = MutableStateFlow<String?>(null)
    val dni: StateFlow<String?> = _dni

    private val _celular = MutableStateFlow<String?>(null)
    val celular: StateFlow<String?> = _celular

    private val _direccion = MutableStateFlow<String?>(null)
    val direccion: StateFlow<String?> = _direccion

    private val _productos = MutableStateFlow<String?>(null)
    val productos: StateFlow<String?> = _productos

    private val _total = MutableStateFlow<String?>(null)
    val total: StateFlow<String?> = _total

    private val _adelanto = MutableStateFlow<String?>(null)
    val adelanto: StateFlow<String?> = _adelanto

    private val _saldo = MutableStateFlow<String?>(null)
    val saldo: StateFlow<String?> = _saldo

    private val _selectedEmpresa = MutableStateFlow<String?>(null)
    val selectedEmpresa: StateFlow<String?> = _selectedEmpresa

    private val _selectedTipoRegistro = MutableStateFlow<String?>(null)
    val selectedTipoRegistro: StateFlow<String?> = _selectedTipoRegistro

    private val _selectedCiudadZona = MutableStateFlow<String?>(null)
    val selectedCiudadZona: StateFlow<String?> = _selectedCiudadZona

    private val _fechaRegistro = MutableStateFlow<String?>(null)
    val fechaRegistro: StateFlow<String?> = _fechaRegistro

    private val _paraElDia = MutableStateFlow<String?>(null)
    val paraElDia: StateFlow<String?> = _paraElDia

    private val _selectedAsesor = MutableStateFlow<String?>(null)
    val selectedAsesor: StateFlow<String?> = _selectedAsesor

    private val _celular2 = MutableStateFlow<String?>(null)
    val celular2: StateFlow<String?> = _celular2

    private val _referencia = MutableStateFlow<String?>(null)
    val referencia: StateFlow<String?> = _referencia

    // ERRORES

    private val _nombreClienteError = MutableStateFlow<String?>(null)
    val nombreClienteError: StateFlow<String?> = _nombreClienteError

    private val _dniError = MutableStateFlow<String?>(null)
    val dniError: StateFlow<String?> = _dniError

    private val _celularError = MutableStateFlow<String?>(null)
    val celularError: StateFlow<String?> = _celularError

    private val _direccionError = MutableStateFlow<String?>(null)
    val direccionError: StateFlow<String?> = _direccionError

    private val _productosError = MutableStateFlow<String?>(null)
    val productosError: StateFlow<String?> = _productosError

    private val _totalError = MutableStateFlow<String?>(null)
    val totalError: StateFlow<String?> = _totalError

    private val _adelantoError = MutableStateFlow<String?>(null)
    val adelantoError: StateFlow<String?> = _adelantoError

    private val _saldoError = MutableStateFlow<String?>(null)
    val saldoError: StateFlow<String?> = _saldoError

    private val _selectedEmpresaError = MutableStateFlow<String?>(null)
    val selectedEmpresaError: StateFlow<String?> = _selectedEmpresaError

    private val _selectedTipoRegistroError = MutableStateFlow<String?>(null)
    val selectedTipoRegistroError: StateFlow<String?> = _selectedTipoRegistroError

    private val _selectedCiudadZonaError = MutableStateFlow<String?>(null)
    val selectedCiudadZonaError: StateFlow<String?> = _selectedCiudadZonaError

    private val _fechaRegistroError = MutableStateFlow<String?>(null)
    val fechaRegistroError: StateFlow<String?> = _fechaRegistroError

    private val _paraElDiaError = MutableStateFlow<String?>(null)
    val paraElDiaError: StateFlow<String?> = _paraElDiaError

    private val _selectedAsesorError = MutableStateFlow<String?>(null)
    val selectedAsesorError: StateFlow<String?> = _selectedAsesorError

    private val _celular2Error = MutableStateFlow<String?>(null)
    val celular2Error: StateFlow<String?> = _celular2Error

    private val _referenciaError = MutableStateFlow<String?>(null)
    val referenciaError: StateFlow<String?> = _referenciaError

    // VARIABLES REGISTROS

    private val _listaRegistros = MutableStateFlow<List<RegistroCamarasSimpleResponse>>(emptyList())
    val listaRegistros: StateFlow<List<RegistroCamarasSimpleResponse>> = _listaRegistros

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

    private val _registroDetalle = MutableStateFlow<RegistroCamarasCompletoResponse?>(null)
    val registroDetalle: StateFlow<RegistroCamarasCompletoResponse?> = _registroDetalle

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
            _state.value = RegistroCamarasState.Loading

            val resultado: Result<PaginationResponse<RegistroCamarasSimpleResponse>> =
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

                _state.value = RegistroCamarasState.Success
            } else {
                _state.value = RegistroCamarasState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun obtenerRegistroDetalle(id: String) {
        viewModelScope.launch {
            _stateRegistroDetalle.value = RegistroCamarasDetalleState.Loading

            val resultado = useCase.obtenerPorId(id)

            if (resultado.isSuccess) {
                val data = resultado.getOrNull()
                if (data != null) {
                    _registroDetalle.value = data
                    _stateRegistroDetalle.value = RegistroCamarasDetalleState.Success
                } else {
                    _stateRegistroDetalle.value = RegistroCamarasDetalleState.Error("Respuesta vacía del servidor")
                }
            } else {
                _stateRegistroDetalle.value = RegistroCamarasDetalleState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun crearNuevoRegistro() {
        viewModelScope.launch {
            _stateAgregarRegistro.value = AgregarRegistroCamarasState.Loading

            val request = RegistroCamarasRequest(
                empresa       = _selectedEmpresa.value,
                tipo          = _selectedTipoRegistro.value,
                lugar         = _selectedCiudadZona.value,
                fechaRegistro = _fechaRegistro.value,
                dia           = _paraElDia.value,
                asesor        = _selectedAsesor.value,
                nombreCliente = _nombreCliente.value,
                dni           = _dni.value,
                celular       = _celular.value,
                celular2      = _celular2.value, // Falta definir MutableStateFlow<String?> para celular2
                direccion     = _direccion.value,
                referencia    = _referencia.value, // Falta definir MutableStateFlow<String?> para referencia
                productos     = _productos.value,
                total         = _total.value,
                adelanto      = _adelanto.value,
                saldo         = _saldo.value
            )

            val resultado = useCase.crearNuevoRegistro(request)

            if (resultado.isSuccess) {
                val mensaje = resultado.getOrNull()?.mensaje
                _stateAgregarRegistro.value = AgregarRegistroCamarasState.Success
            } else {
                _stateAgregarRegistro.value = AgregarRegistroCamarasState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido al registrar."
                )
            }
        }
    }

    // FUNCIONES DE CAMBIO DE VALORES DE VARIABLES

    fun onNombreClienteChange(value: String) {
        _nombreCliente.value = value
    }

    fun onDniChange(value: String) {
        _dni.value = value
    }

    fun onCelularChange(value: String) {
        _celular.value = value
    }

    fun onDireccionChange(value: String) {
        _direccion.value = value
    }

    fun onProductosChange(value: String) {
        _productos.value = value
    }

    fun onTotalChange(value: String) {
        _total.value = value
    }

    fun onAdelantoChange(value: String) {
        _adelanto.value = value
    }

    fun onSaldoChange(value: String) {
        _saldo.value = value
    }

    fun onSelectedEmpresaChange(value: String) {
        _selectedEmpresa.value = value
    }

    fun onSelectedTipoRegistroChange(value: String) {
        _selectedTipoRegistro.value = value
    }

    fun onSelectedCiudadZonaChange(value: String) {
        _selectedCiudadZona.value = value
    }

    fun onFechaRegistroChange(value: String) {
        _fechaRegistro.value = value
    }

    fun onParaElDiaChange(value: String) {
        _paraElDia.value = value
    }

    fun onSelectedAsesorChange(value: String) {
        _selectedAsesor.value = value
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

    // VALIDACIONES DE VARIABLES

    fun validarNombreCliente() {
        _nombreClienteError.value = if (_nombreCliente.value.isNullOrBlank()) "El nombre del cliente es obligatorio" else null
    }

    fun validarDni() {
        _dniError.value = if (_dni.value.isNullOrBlank()) "El DNI es obligatorio" else null
    }

    fun validarCelular() {
        _celularError.value = if (_celular.value.isNullOrBlank()) "El celular es obligatorio" else null
    }

    fun validarDireccion() {
        _direccionError.value = if (_direccion.value.isNullOrBlank()) "La dirección es obligatoria" else null
    }

    fun validarProductos() {
        _productosError.value = if (_productos.value.isNullOrBlank()) "Los productos son obligatorios" else null
    }

    fun validarTotal() {
        _totalError.value = if (_total.value.isNullOrBlank()) "El total es obligatorio" else null
    }

    fun validarAdelanto() {
        _adelantoError.value = if (_adelanto.value.isNullOrBlank()) "El adelanto es obligatorio" else null
    }

    fun validarSaldo() {
        _saldoError.value = if (_saldo.value.isNullOrBlank()) "El saldo es obligatorio" else null
    }

    fun validarSelectedEmpresa() {
        _selectedEmpresaError.value = if (_selectedEmpresa.value.isNullOrBlank()) "La empresa es obligatoria" else null
    }

    fun validarSelectedTipoRegistro() {
        _selectedTipoRegistroError.value = if (_selectedTipoRegistro.value.isNullOrBlank()) "El tipo de registro es obligatorio" else null
    }

    fun validarSelectedCiudadZona() {
        _selectedCiudadZonaError.value = if (_selectedCiudadZona.value.isNullOrBlank()) "La ciudad o zona es obligatoria" else null
    }

    fun validarFechaRegistro() {
        _fechaRegistroError.value = if (_fechaRegistro.value.isNullOrBlank()) "La fecha de registro es obligatoria" else null
    }

    fun validarParaElDia() {
        _paraElDiaError.value = if (_paraElDia.value.isNullOrBlank()) "La fecha para el día es obligatoria" else null
    }

    fun validarSelectedAsesor() {
        _selectedAsesorError.value = if (_selectedAsesor.value.isNullOrBlank()) "El asesor es obligatorio" else null
    }

    fun validarReferencia() {
        _referenciaError.value = if (_referencia.value.isNullOrBlank()) "La fecha de recojo es obligatoria" else null
    }

    fun validarCelular2() {
        _celular2Error.value = if (_celular2.value.isNullOrBlank()) "Debe indicar si es para el día" else null
    }

    fun resetStateAgregarRegistro() {
        _stateAgregarRegistro.value = AgregarRegistroCamarasState.Init
    }

}