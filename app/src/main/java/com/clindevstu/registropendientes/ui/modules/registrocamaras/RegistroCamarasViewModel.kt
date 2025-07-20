package com.clindevstu.registropendientes.ui.modules.registrocamaras

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.responses.PaginationResponse
import com.clindevstu.registropendientes.core.models.responses.RegistroCamarasSimpleResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistroCamarasViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private var _state = MutableStateFlow<RegistroCamarasState>(RegistroCamarasState.Init)
    val state: StateFlow<RegistroCamarasState> = _state

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

    // DATOS PARA MOSTRAR

    private val _paginaRegistroCamaras = MutableStateFlow<PaginationResponse<RegistroCamarasSimpleResponse>?>(null)
    val paginaRegistroCamaras: StateFlow<PaginationResponse<RegistroCamarasSimpleResponse>?> = _paginaRegistroCamaras

    private val _listaRegistroCamaras = MutableStateFlow<List<RegistroCamarasSimpleResponse>?>(null)
    val listaRegistroCamaras: StateFlow<List<RegistroCamarasSimpleResponse>?> = _listaRegistroCamaras

    // VARIABLES DE CONTEXTO

    private val _usuario = MutableStateFlow<UsuarioResponse>(UsuarioResponse())
    val usuario: StateFlow<UsuarioResponse> = _usuario.asStateFlow()

    private val _userPreferences = MutableStateFlow<UserPreferences?>(null)
    val userPreferences: StateFlow<UserPreferences?> = _userPreferences.asStateFlow()

    // FUNCION DE CONTEXTO

    fun obtenerContexto(context: Context){

        _userPreferences.value =  UserPreferences(context)
        viewModelScope.launch {
            _userPreferences.value?.userData?.collect { user ->
                _usuario.value = user
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

}