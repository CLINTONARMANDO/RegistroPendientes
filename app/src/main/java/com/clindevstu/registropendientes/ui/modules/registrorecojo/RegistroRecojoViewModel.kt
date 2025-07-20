package com.clindevstu.registropendientes.ui.modules.registrorecojo

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.ui.modules.registrocamaras.RegistroCamarasState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistroRecojoViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application){

    private var _state = MutableStateFlow<RegistroCamarasState>(RegistroCamarasState.Init)
    val state: StateFlow<RegistroCamarasState> = _state

    // VARIABLES DEL FORMULARIO


    private val _codigoUsuario = MutableStateFlow<String?>(null)
    val codigoUsuario: StateFlow<String?> = _codigoUsuario

    private val _nombreCliente = MutableStateFlow<String?>(null)
    val nombreCliente: StateFlow<String?> = _nombreCliente

    private val _celular = MutableStateFlow<String?>(null)
    val celular: StateFlow<String?> = _celular

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


}