package com.clindevstu.registropendientes.ui.modules.panelcentral.configuraciones

import com.clindevstu.registropendientes.ui.modules.panelcentral.PanelCentralState

sealed class ConfiguracionesState {
    object Init : ConfiguracionesState()
    object Loading : ConfiguracionesState()
    object Success : ConfiguracionesState()
    data class Error(val message: String) : ConfiguracionesState()
}