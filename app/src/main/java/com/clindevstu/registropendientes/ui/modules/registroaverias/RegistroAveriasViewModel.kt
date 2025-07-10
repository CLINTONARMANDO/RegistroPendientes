package com.clindevstu.registropendientes.ui.modules.registroaverias

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class RegistroAveriasViewModel @Inject constructor(
    val application: Application
) : AndroidViewModel(application){
    private var _state = mutableStateOf<RegistroAveriasState>(RegistroAveriasState.Init)
    val state: RegistroAveriasState get() = _state.value

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

    // Errores
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

    // ERRORES
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

}