package com.clindevstu.registropendientes.ui.modules.registrointernet

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.requests.RegistroInternetRequest
import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroInternetCompletoResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroInternetSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.domain.usecase.RegistroInternetUseCase
import com.clindevstu.registropendientes.ui.modules.registrointernet.agregarregistrointernet.AgregarRegistroInternetState
import com.clindevstu.registropendientes.ui.modules.registrointernet.registrointernetdetalle.RegistroInternetDetalleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroInternetViewModel @Inject constructor(
    private val useCase: RegistroInternetUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow<RegistroInternetState>(RegistroInternetState.Init)
    val state: StateFlow<RegistroInternetState> = _state

    private var _stateAgregarRegistro = MutableStateFlow<AgregarRegistroInternetState>(AgregarRegistroInternetState.Init)
    val stateAgregarRegistro: StateFlow<AgregarRegistroInternetState> = _stateAgregarRegistro

    private var _stateRegistroDetalle = MutableStateFlow<RegistroInternetDetalleState>(RegistroInternetDetalleState.Init)
    val stateRegistroDetalle: StateFlow<RegistroInternetDetalleState> = _stateRegistroDetalle

    // VARIABLES FORMULARIO

    private val _codigoRegistro = MutableStateFlow<String?>(null)
    val codigoRegistro: StateFlow<String?> = _codigoRegistro.asStateFlow()

    private val _nombreCliente = MutableStateFlow<String?>(null)
    val nombreCliente: StateFlow<String?> = _nombreCliente.asStateFlow()

    private val _dni = MutableStateFlow<String?>(null)
    val dni: StateFlow<String?> = _dni.asStateFlow()

    private val _celular = MutableStateFlow<String?>(null)
    val celular: StateFlow<String?> = _celular.asStateFlow()

    private val _direccion = MutableStateFlow<String?>(null)
    val direccion: StateFlow<String?> = _direccion.asStateFlow()

    private val _descripcion = MutableStateFlow<String?>(null)
    val descripcion: StateFlow<String?> = _descripcion.asStateFlow()

    private val _selectedAsesor = MutableStateFlow<String?>(null)
    val selectedAsesor: StateFlow<String?> = _selectedAsesor.asStateFlow()

    private val _selectedEmpresa = MutableStateFlow<String?>(null)
    val selectedEmpresa: StateFlow<String?> = _selectedEmpresa.asStateFlow()

    private val _selectedFechaRegistro = MutableStateFlow<String?>(null)
    val selectedFechaRegistro: StateFlow<String?> = _selectedFechaRegistro.asStateFlow()

    private val _selectedCiudadZona = MutableStateFlow<String?>(null)
    val selectedCiudadZona: StateFlow<String?> = _selectedCiudadZona.asStateFlow()

    private val _selectedParaElDia = MutableStateFlow<String?>(null)
    val selectedParaElDia: StateFlow<String?> = _selectedParaElDia.asStateFlow()

    private val _selectedTipoRegistro = MutableStateFlow<String?>(null)
    val selectedTipoRegistro: StateFlow<String?> = _selectedTipoRegistro.asStateFlow()

    private val _selectedPrioridad = MutableStateFlow<String?>(null)
    val selectedPrioridad: StateFlow<String?> = _selectedPrioridad.asStateFlow()

    private val _plan = MutableStateFlow<String?>(null)
    val plan: StateFlow<String?> = _plan.asStateFlow()

    private val _costoPlan = MutableStateFlow<String?>(null)
    val costoPlan: StateFlow<String?> = _costoPlan.asStateFlow()

    private val _costoInst = MutableStateFlow<String?>(null)
    val costoInst: StateFlow<String?> = _costoInst.asStateFlow()

    private val _promocion = MutableStateFlow<String?>(null)
    val promocion: StateFlow<String?> = _promocion.asStateFlow()

    private val _kitAntena = MutableStateFlow<String?>(null)
    val kitAntena: StateFlow<String?> = _kitAntena.asStateFlow()

    private val _costoKit = MutableStateFlow<String?>(null)
    val costoKit: StateFlow<String?> = _costoKit.asStateFlow()

    private val _costoAntena = MutableStateFlow<String?>(null)
    val costoAntena: StateFlow<String?> = _costoAntena.asStateFlow()

    private val _dias = MutableStateFlow<String?>(null)
    val dias: StateFlow<String?> = _dias.asStateFlow()

    private val _adelanto = MutableStateFlow<String?>(null)
    val adelanto: StateFlow<String?> = _adelanto.asStateFlow()

    private val _total = MutableStateFlow<String?>(null)
    val total: StateFlow<String?> = _total.asStateFlow()

    private val _celular2 = MutableStateFlow<String?>(null)
    val celular2: StateFlow<String?> = _celular2

    private val _referencia = MutableStateFlow<String?>(null)
    val referencia: StateFlow<String?> = _referencia

    // ERRORES

    private val _codigoRegistroError = MutableStateFlow<String?>(null)
    val codigoRegistroError: StateFlow<String?> = _codigoRegistroError.asStateFlow()

    private val _nombreClienteError = MutableStateFlow<String?>(null)
    val nombreClienteError: StateFlow<String?> = _nombreClienteError.asStateFlow()

    private val _dniError = MutableStateFlow<String?>(null)
    val dniError: StateFlow<String?> = _dniError.asStateFlow()

    private val _celularError = MutableStateFlow<String?>(null)
    val celularError: StateFlow<String?> = _celularError.asStateFlow()

    private val _direccionError = MutableStateFlow<String?>(null)
    val direccionError: StateFlow<String?> = _direccionError.asStateFlow()

    private val _notaImportanteError = MutableStateFlow<String?>(null)
    val notaImportanteError: StateFlow<String?> = _notaImportanteError.asStateFlow()

    private val _selectedAsesorError = MutableStateFlow<String?>(null)
    val selectedAsesorError: StateFlow<String?> = _selectedAsesorError.asStateFlow()

    private val _selectedEmpresaError = MutableStateFlow<String?>(null)
    val selectedEmpresaError: StateFlow<String?> = _selectedEmpresaError.asStateFlow()

    private val _selectedFechaRegistroError = MutableStateFlow<String?>(null)
    val selectedFechaRegistroError: StateFlow<String?> = _selectedFechaRegistroError.asStateFlow()

    private val _selectedCiudadZonaError = MutableStateFlow<String?>(null)
    val selectedCiudadZonaError: StateFlow<String?> = _selectedCiudadZonaError.asStateFlow()

    private val _selectedParaElDiaError = MutableStateFlow<String?>(null)
    val selectedParaElDiaError: StateFlow<String?> = _selectedParaElDiaError.asStateFlow()

    private val _selectedTipoRegistroError = MutableStateFlow<String?>(null)
    val selectedTipoRegistroError: StateFlow<String?> = _selectedTipoRegistroError.asStateFlow()

    private val _selectedPrioridadError = MutableStateFlow<String?>(null)
    val selectedPrioridadError: StateFlow<String?> = _selectedPrioridadError.asStateFlow()

    private val _planError = MutableStateFlow<String?>(null)
    val planError: StateFlow<String?> = _planError.asStateFlow()

    private val _costoPlanError = MutableStateFlow<String?>(null)
    val costoPlanError: StateFlow<String?> = _costoPlanError.asStateFlow()

    private val _costoInstError = MutableStateFlow<String?>(null)
    val costoInstError: StateFlow<String?> = _costoInstError.asStateFlow()

    private val _promocionError = MutableStateFlow<String?>(null)
    val promocionError: StateFlow<String?> = _promocionError.asStateFlow()

    private val _kitAntenaError = MutableStateFlow<String?>(null)
    val kitAntenaError: StateFlow<String?> = _kitAntenaError.asStateFlow()

    private val _costoKitError = MutableStateFlow<String?>(null)
    val costoKitError: StateFlow<String?> = _costoKitError.asStateFlow()

    private val _costoAntenaError = MutableStateFlow<String?>(null)
    val costoAntenaError: StateFlow<String?> = _costoAntenaError.asStateFlow()

    private val _diasError = MutableStateFlow<String?>(null)
    val diasError: StateFlow<String?> = _diasError.asStateFlow()

    private val _adelantoError = MutableStateFlow<String?>(null)
    val adelantoError: StateFlow<String?> = _adelantoError.asStateFlow()

    private val _totalError = MutableStateFlow<String?>(null)
    val totalError: StateFlow<String?> = _totalError.asStateFlow()

    private val _celular2Error = MutableStateFlow<String?>(null)
    val celular2Error: StateFlow<String?> = _celular2Error

    private val _referenciaError = MutableStateFlow<String?>(null)
    val referenciaError: StateFlow<String?> = _referenciaError

    private val _descripcionError = MutableStateFlow<String?>(null)
    val descripcionError: StateFlow<String?> = _descripcionError

    // VARIABLES REGISTROS

    private val _listaRegistros = MutableStateFlow<List<RegistroInternetSimpleResponse>>(emptyList())
    val listaRegistros: StateFlow<List<RegistroInternetSimpleResponse>> = _listaRegistros

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

    private val _registroDetalle = MutableStateFlow<RegistroInternetCompletoResponse?>(null)
    val registroDetalle: StateFlow<RegistroInternetCompletoResponse?> = _registroDetalle

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
            _state.value = RegistroInternetState.Loading
            Log.i("obtenerRegistros", _usuario.value.esTecnico.toString())
            val resultado: Result<PaginationResponse<RegistroInternetSimpleResponse>> =
                if (_usuario.value.esTecnico) {
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

                _state.value = RegistroInternetState.Success
            } else {
                _state.value = RegistroInternetState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun obtenerRegistroDetalle(id: String) {
        viewModelScope.launch {
            _stateRegistroDetalle.value = RegistroInternetDetalleState.Loading

            val resultado = useCase.obtenerPorId(id)

            if (resultado.isSuccess) {
                val data = resultado.getOrNull()
                if (data != null) {
                    _registroDetalle.value = data
                    _stateRegistroDetalle.value = RegistroInternetDetalleState.Success
                } else {
                    _stateRegistroDetalle.value = RegistroInternetDetalleState.Error("Respuesta vacía del servidor")
                }
            } else {
                _stateRegistroDetalle.value = RegistroInternetDetalleState.Error(
                    resultado.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }

    fun crearNuevoRegistro() {
        viewModelScope.launch {
            _stateAgregarRegistro.value = AgregarRegistroInternetState.Loading

            val request = RegistroInternetRequest(
                codigoGrande = _codigoRegistro.value,
                asesorVentas = _selectedAsesor.value,
                empresa = _selectedEmpresa.value,
                fechaRegistro = _selectedFechaRegistro.value?.toString(),
                nombreCliente = _nombreCliente.value,
                dni = _dni.value,
                celulares = _celular.value + _celular2.value,
                direccion = _direccion.value + _referencia.value,
                lugar = _selectedCiudadZona.value,
                diaInstalacion = _selectedParaElDia.value?.toString(),
                otrosDetalles = _descripcion.value,
                tipo = _selectedTipoRegistro.value,
                prioridad = _selectedPrioridad.value,
                plan = _plan.value,
                costoPlan = _costoPlan.value,
                costoInst = _costoInst.value,
                promocion = _promocion.value,
                kitAntena = _kitAntena.value,
                costoKit = _costoKit.value,
                costoAntena = _costoAntena.value,
                dias = _dias.value,
                adelanto = _adelanto.value,
                total = _total.value,
            )

            val resultado = useCase.crearNuevoRegistro(request)

            if (resultado.isSuccess) {
                val mensaje = resultado.getOrNull()?.mensaje
                _stateAgregarRegistro.value = AgregarRegistroInternetState.Success
            } else {
                _stateAgregarRegistro.value = AgregarRegistroInternetState.Error(
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

    fun onDniChange(value: String) {
        _dni.value = value
    }

    fun onCelularChange(value: String) {
        _celular.value = value
    }

    fun onDireccionChange(value: String) {
        _direccion.value = value
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

    fun onSelectedFechaRegistroChange(value: String) {
        _selectedFechaRegistro.value = value
    }

    fun onSelectedCiudadZonaChange(value: String) {
        _selectedCiudadZona.value = value
    }

    fun onSelectedParaElDiaChange(value: String) {
        _selectedParaElDia.value = value
    }

    fun onSelectedTipoRegistroChange(value: String) {
        _selectedTipoRegistro.value = value
    }

    fun onSelectedPrioridadChange(value: String) {
        _selectedPrioridad.value = value
    }

    fun onPlanChange(value: String) {
        _plan.value = value
    }

    fun onCostoPlanChange(value: String) {
        _costoPlan.value = value
    }

    fun onCostoInstChange(value: String) {
        _costoInst.value = value
    }

    fun onPromocionChange(value: String) {
        _promocion.value = value
    }

    fun onKitAntenaChange(value: String) {
        _kitAntena.value = value
    }

    fun onCostoKitChange(value: String) {
        _costoKit.value = value
    }

    fun onCostoAntenaChange(value: String) {
        _costoAntena.value = value
    }

    fun onDiasChange(value: String) {
        _dias.value = value
    }

    fun onAdelantoChange(value: String) {
        _adelanto.value = value
    }

    fun onTotalChange(value: String) {
        _total.value = value
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

    // FUNCION DE VALIDACION DE FORMULARIO

    fun validarFormulario(): Boolean {
        validarCodigoRegistro()
        validarNombreCliente()
        validarDni()
        validarCelular()
        validarDireccion()
        validarDescripcion()
        validarSelectedAsesor()
        validarSelectedEmpresa()
        validarSelectedFechaRegistro()
        validarSelectedCiudadZona()
        validarSelectedParaElDia()
        validarSelectedTipoRegistro()
        validarSelectedPrioridad()

        return listOf(
            _codigoRegistroError,
            _nombreClienteError,
            _dniError,
            _celularError,
            _direccionError,
            _notaImportanteError,
            _selectedAsesorError,
            _selectedEmpresaError,
            _selectedFechaRegistroError,
            _selectedCiudadZonaError,
            _selectedParaElDiaError,
            _selectedTipoRegistroError,
            _selectedPrioridadError
        ).all { it.value == null }
    }

    //FUNCIONES DE VALIDACION PARA ERRORES POR VARIABLE

    fun validarCodigoRegistro() {
        _codigoRegistroError.value = if (_codigoRegistro.value.isNullOrBlank()) "El código de registro es obligatorio" else null
    }

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

    fun validarDescripcion() {
        _notaImportanteError.value = if (_descripcion.value.isNullOrBlank()) "La nota importante es obligatoria" else null
    }

    fun validarSelectedAsesor() {
        _selectedAsesorError.value = if (_selectedAsesor.value.isNullOrBlank()) "Debe seleccionar un asesor" else null
    }

    fun validarSelectedEmpresa() {
        _selectedEmpresaError.value = if (_selectedEmpresa.value.isNullOrBlank()) "Debe seleccionar una empresa" else null
    }

    fun validarSelectedFechaRegistro() {
        _selectedFechaRegistroError.value = if (_selectedFechaRegistro.value == null) "Debe seleccionar una fecha de registro" else null
    }

    fun validarSelectedCiudadZona() {
        _selectedCiudadZonaError.value = if (_selectedCiudadZona.value.isNullOrBlank()) "Debe ingresar la ciudad o zona" else null
    }

    fun validarSelectedParaElDia() {
        _selectedParaElDiaError.value = if (_selectedParaElDia.value == null) "Debe seleccionar una fecha para el día" else null
    }

    fun validarSelectedTipoRegistro() {
        _selectedTipoRegistroError.value = if (_selectedTipoRegistro.value.isNullOrBlank()) "Debe seleccionar un tipo" else null
    }

    fun validarSelectedPrioridad() {
        _selectedPrioridadError.value = if (_selectedPrioridad.value.isNullOrBlank()) "Debe seleccionar un tipo de prioridad" else null
    }

    fun validarPlan() {
        _planError.value = if (_plan.value.isNullOrBlank()) "Debe ingresar el plan" else null
    }

    fun validarCostoPlan() {
        _costoPlanError.value = if (_costoPlan.value.isNullOrBlank()) "Debe ingresar el costo del plan" else null
    }

    fun validarCostoInst() {
        _costoInstError.value = if (_costoInst.value.isNullOrBlank()) "Debe ingresar el costo de instalación" else null
    }

    fun validarPromocion() {
        _promocionError.value = if (_promocion.value.isNullOrBlank()) "Debe ingresar la promoción" else null
    }

    fun validarKitAntena() {
        _kitAntenaError.value = if (_kitAntena.value.isNullOrBlank()) "Debe ingresar el kit o antena" else null
    }

    fun validarCostoKit() {
        _costoKitError.value = if (_costoKit.value.isNullOrBlank()) "Debe ingresar el costo del kit" else null
    }

    fun validarCostoAntena() {
        _costoAntenaError.value = if (_costoAntena.value.isNullOrBlank()) "Debe ingresar el costo de la antena" else null
    }

    fun validarDias() {
        _diasError.value = if (_dias.value.isNullOrBlank()) "Debe ingresar la cantidad de días" else null
    }

    fun validarAdelanto() {
        _adelantoError.value = if (_adelanto.value.isNullOrBlank()) "Debe ingresar el adelanto" else null
    }

    fun validarTotal() {
        _totalError.value = if (_total.value.isNullOrBlank()) "Debe ingresar el total" else null
    }

    fun validarReferencia() {
        _referenciaError.value = if (_referencia.value.isNullOrBlank()) "La fecha de recojo es obligatoria" else null
    }

    fun validarCelular2() {
        _celular2Error.value = if (_celular2.value.isNullOrBlank()) "Debe indicar si es para el día" else null
    }

    fun resetStateAgregarRegistro() {
        _stateAgregarRegistro.value = AgregarRegistroInternetState.Init
    }

}