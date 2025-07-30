package com.clindevstu.registropendientes.ui.modules.registrorecojo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.requests.RegistroRecojoRequest
import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroRecojoCompletoResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroRecojoSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.domain.usecase.RegistroRecojoUseCase
import com.clindevstu.registropendientes.ui.modules.registrorecojo.agregarregistrorecojo.AgregarRegistroRecojoState
import com.clindevstu.registropendientes.ui.modules.registrorecojo.registrorecojodetalle.RegistroRecojoDetalleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroRecojoViewModel @Inject constructor(
    private val useCase: RegistroRecojoUseCase
) : ViewModel(){

    private var _state = MutableStateFlow<RegistroRecojoState>(RegistroRecojoState.Init)
    val state: StateFlow<RegistroRecojoState> = _state

    private var _stateAgregarRegistro = MutableStateFlow<AgregarRegistroRecojoState>(AgregarRegistroRecojoState.Init)
    val stateAgregarRegistro: StateFlow<AgregarRegistroRecojoState> = _stateAgregarRegistro

    private var _stateRegistroDetalle = MutableStateFlow<RegistroRecojoDetalleState>(RegistroRecojoDetalleState.Init)
    val stateRegistroDetalle: StateFlow<RegistroRecojoDetalleState> = _stateRegistroDetalle

    // VARIABLES DEL FORMULARIO

    private val _codigoUsuario = MutableStateFlow<String?>(null)
    val codigoUsuario: StateFlow<String?> = _codigoUsuario

    private val _nombreCliente = MutableStateFlow<String?>(null)
    val nombreCliente: StateFlow<String?> = _nombreCliente

    private val _celular = MutableStateFlow<String?>(null)
    val celular: StateFlow<String?> = _celular

    private val _celular2 = MutableStateFlow<String?>(null)
    val celular2: StateFlow<String?> = _celular2

    private val _referencia = MutableStateFlow<String?>(null)
    val referencia: StateFlow<String?> = _referencia

    private val _direccion = MutableStateFlow<String?>(null)
    val direccion: StateFlow<String?> = _direccion

    private val _motivo = MutableStateFlow<String?>(null)
    val motivo: StateFlow<String?> = _motivo

    private val _selectedAsesor = MutableStateFlow<String?>(null)
    val selectedAsesor: StateFlow<String?> = _selectedAsesor

    private val _selectedCiudadZona = MutableStateFlow<String?>(null)
    val selectedCiudadZona: StateFlow<String?> = _selectedCiudadZona

    private val _selectedEmpresa = MutableStateFlow<String?>(null)
    val selectedEmpresa: StateFlow<String?> = _selectedEmpresa

    private val _selectedTipoRegistro = MutableStateFlow<String?>(null)
    val selectedTipoRegistro: StateFlow<String?> = _selectedTipoRegistro

    private val _selectedFechaRegistro = MutableStateFlow<String?>(null)
    val selectedFechaRegistro: StateFlow<String?> = _selectedFechaRegistro

    private val _selectedFechaRecojo = MutableStateFlow<String?>(null)
    val selectedFechaRecojo: StateFlow<String?> = _selectedFechaRecojo

    private val _selectedParaElDia = MutableStateFlow<String?>(null)
    val selectedParaElDia: StateFlow<String?> = _selectedParaElDia

    // VARIABLES DE ERROR

    private val _codigoUsuarioError = MutableStateFlow<String?>(null)
    val codigoUsuarioError: StateFlow<String?> = _codigoUsuarioError

    private val _nombreClienteError = MutableStateFlow<String?>(null)
    val nombreClienteError: StateFlow<String?> = _nombreClienteError

    private val _celularError = MutableStateFlow<String?>(null)
    val celularError: StateFlow<String?> = _celularError

    private val _direccionError = MutableStateFlow<String?>(null)
    val direccionError: StateFlow<String?> = _direccionError

    private val _motivoError = MutableStateFlow<String?>(null)
    val motivoError: StateFlow<String?> = _motivoError

    private val _asesorError = MutableStateFlow<String?>(null)
    val asesorError: StateFlow<String?> = _asesorError

    private val _ciudadZonaError = MutableStateFlow<String?>(null)
    val ciudadZonaError: StateFlow<String?> = _ciudadZonaError

    private val _empresaError = MutableStateFlow<String?>(null)
    val empresaError: StateFlow<String?> = _empresaError

    private val _tipoRegistroError = MutableStateFlow<String?>(null)
    val tipoRegistroError: StateFlow<String?> = _tipoRegistroError

    private val _fechaRegistroError = MutableStateFlow<String?>(null)
    val fechaRegistroError: StateFlow<String?> = _fechaRegistroError

    private val _fechaRecojoError = MutableStateFlow<String?>(null)
    val fechaRecojoError: StateFlow<String?> = _fechaRecojoError

    private val _paraElDiaError = MutableStateFlow<String?>(null)
    val paraElDiaError: StateFlow<String?> = _paraElDiaError

    private val _celular2Error = MutableStateFlow<String?>(null)
    val celular2Error: StateFlow<String?> = _celular2Error

    private val _referenciaError = MutableStateFlow<String?>(null)
    val referenciaError: StateFlow<String?> = _referenciaError

    // VARIABLES REGISTROS

    private val _listaRegistros = MutableStateFlow<List<RegistroRecojoSimpleResponse>>(emptyList())
    val listaRegistros: StateFlow<List<RegistroRecojoSimpleResponse>> = _listaRegistros

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

    private val _registroDetalle = MutableStateFlow<RegistroRecojoCompletoResponse?>(null)
    val registroDetalle: StateFlow<RegistroRecojoCompletoResponse?> = _registroDetalle

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
            _state.value = RegistroRecojoState.Loading

            val resultado: Result<PaginationResponse<RegistroRecojoSimpleResponse>> =
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

                _state.value = RegistroRecojoState.Success
            } else {
                _state.value = RegistroRecojoState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun obtenerRegistroDetalle(id: String) {
        viewModelScope.launch {
            _stateRegistroDetalle.value = RegistroRecojoDetalleState.Loading

            val resultado = useCase.obtenerPorId(id)

            if (resultado.isSuccess) {
                val data = resultado.getOrNull()
                if (data != null) {
                    _registroDetalle.value = data
                    _stateRegistroDetalle.value = RegistroRecojoDetalleState.Success
                } else {
                    _stateRegistroDetalle.value = RegistroRecojoDetalleState.Error("Respuesta vacía del servidor")
                }
            } else {
                _stateRegistroDetalle.value = RegistroRecojoDetalleState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun crearNuevoRegistro() {
        viewModelScope.launch {
            _stateAgregarRegistro.value = AgregarRegistroRecojoState.Loading

            val request = RegistroRecojoRequest(
                asesor = _selectedAsesor.value,
                codigoUsuario = _codigoUsuario.value,
                nombreCliente = _nombreCliente.value,
                lugar = _selectedCiudadZona.value,
                empresa = _selectedEmpresa.value,
                dispositivo = _selectedTipoRegistro.value,
                fechaRegistro = _selectedFechaRegistro.value,
                fechaRecojo = _selectedFechaRecojo.value,
                dia = _selectedParaElDia.value,
                celular = _celular.value,
                celular2 = _celular2.value,
                direccion = _direccion.value,
                referencia = _referencia.value,
                motivoRecojo = _motivo.value
            )

            val resultado = useCase.crearNuevoRegistro(request)

            if (resultado.isSuccess) {
                val mensaje = resultado.getOrNull()?.mensaje
                _stateAgregarRegistro.value = AgregarRegistroRecojoState.Success
            } else {
                _stateAgregarRegistro.value = AgregarRegistroRecojoState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido al registrar."
                )
            }
        }
    }

    // FUNCIONES DE CAMBIO DE VALOR PARA CADA VARIABLE DEL FORMULARIO

    fun onCodigoUsuarioChange(value: String) {
        _codigoUsuario.value = value
    }

    fun onNombreClienteChange(value: String) {
        _nombreCliente.value = value
    }

    fun onCelularChange(value: String) {
        _celular.value = value
    }

    fun onDireccionChange(value: String) {
        _direccion.value = value
    }

    fun onMotivoChange(value: String) {
        _motivo.value = value
    }

    fun onAsesorChange(value: String) {
        _selectedAsesor.value = value
    }

    fun onCiudadZonaChange(value: String) {
        _selectedCiudadZona.value = value
    }

    fun onEmpresaChange(value: String) {
        _selectedEmpresa.value = value
    }

    fun onTipoRegistroChange(value: String) {
        _selectedTipoRegistro.value = value
    }

    fun onFechaRegistroChange(value: String) {
        _selectedFechaRegistro.value = value
    }

    fun onFechaRecojoChange(value: String) {
        _selectedFechaRecojo.value = value
    }

    fun onParaElDiaChange(value: String) {
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

    // VALIDACIONES

    fun validarCodigoUsuario() {
        _codigoUsuarioError.value = if (_codigoUsuario.value.isNullOrBlank()) "El código de usuario es obligatorio" else null
    }

    fun validarNombreCliente() {
        _nombreClienteError.value = if (_nombreCliente.value.isNullOrBlank()) "El nombre del cliente es obligatorio" else null
    }

    fun validarCelular() {
        _celularError.value = if (_celular.value.isNullOrBlank()) "El número de celular es obligatorio" else null
    }

    fun validarDireccion() {
        _direccionError.value = if (_direccion.value.isNullOrBlank()) "La dirección es obligatoria" else null
    }

    fun validarMotivo() {
        _motivoError.value = if (_motivo.value.isNullOrBlank()) "El motivo es obligatorio" else null
    }

    fun validarAsesor() {
        _asesorError.value = if (_selectedAsesor.value.isNullOrBlank()) "Debe seleccionar un asesor" else null
    }

    fun validarCiudadZona() {
        _ciudadZonaError.value = if (_selectedCiudadZona.value.isNullOrBlank()) "Debe seleccionar una ciudad o zona" else null
    }

    fun validarEmpresa() {
        _empresaError.value = if (_selectedEmpresa.value.isNullOrBlank()) "Debe seleccionar una empresa" else null
    }

    fun validarTipoRegistro() {
        _tipoRegistroError.value = if (_selectedTipoRegistro.value.isNullOrBlank()) "Debe seleccionar un tipo de registro" else null
    }

    fun validarFechaRegistro() {
        _fechaRegistroError.value = if (_selectedFechaRegistro.value.isNullOrBlank()) "La fecha de registro es obligatoria" else null
    }

    fun validarFechaRecojo() {
        _fechaRecojoError.value = if (_selectedFechaRecojo.value.isNullOrBlank()) "La fecha de recojo es obligatoria" else null
    }

    fun validarParaElDia() {
        _paraElDiaError.value = if (_selectedParaElDia.value.isNullOrBlank()) "Debe indicar si es para el día" else null
    }

    fun validarReferencia() {
        _referenciaError.value = if (_referencia.value.isNullOrBlank()) "La fecha de recojo es obligatoria" else null
    }

    fun validarCelular2() {
        _celular2Error.value = if (_celular2.value.isNullOrBlank()) "Debe indicar si es para el día" else null
    }

    fun resetStateAgregarRegistro() {
        _stateAgregarRegistro.value = AgregarRegistroRecojoState.Init
    }


}