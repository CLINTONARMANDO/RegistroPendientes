package com.clindevstu.registropendientes.ui.modules.registrointernet

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import javax.inject.Inject

class RegistroInternetViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private var _state = MutableStateFlow<RegistroInternetState>(RegistroInternetState.Init)
    val state: StateFlow<RegistroInternetState> = _state

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

    private val _selectedFechaRegistro = MutableStateFlow<LocalDate?>(null)
    val selectedFechaRegistro: StateFlow<LocalDate?> = _selectedFechaRegistro.asStateFlow()

    private val _selectedCiudadZona = MutableStateFlow<String?>(null)
    val selectedCiudadZona: StateFlow<String?> = _selectedCiudadZona.asStateFlow()

    private val _selectedParaElDia = MutableStateFlow<LocalDate?>(null)
    val selectedParaElDia: StateFlow<LocalDate?> = _selectedParaElDia.asStateFlow()

    private val _selectedTipoRegistro = MutableStateFlow<String?>(null)
    val selectedTipoRegistro: StateFlow<String?> = _selectedTipoRegistro.asStateFlow()

    private val _selectedPrioridad = MutableStateFlow<String?>(null)
    val selectedPrioridad: StateFlow<String?> = _selectedPrioridad.asStateFlow()



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

    fun onDescripcion(value: String) {
        _descripcion.value = value
    }

    fun onSelectedAsesorChange(value: String) {
        _selectedAsesor.value = value
    }

    fun onSelectedEmpresaChange(value: String) {
        _selectedEmpresa.value = value
    }

    fun onSelectedFechaRegistroChange(value: LocalDate) {
        _selectedFechaRegistro.value = value
    }

    fun onSelectedCiudadZonaChange(value: String) {
        _selectedCiudadZona.value = value
    }

    fun onSelectedParaElDiaChange(value: LocalDate) {
        _selectedParaElDia.value = value
    }

    fun onSelectedTipoRegistroChange(value: String) {
        _selectedTipoRegistro.value = value
    }

    fun onSelectedPrioridadChange(value: String) {
        _selectedPrioridad.value = value
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

}