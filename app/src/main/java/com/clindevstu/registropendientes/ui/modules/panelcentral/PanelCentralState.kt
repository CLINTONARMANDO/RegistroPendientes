package com.clindevstu.registropendientes.ui.modules.panelcentral

import com.clindevstu.registropendientes.ui.modules.splashprincipal.SplashPrincipalState

sealed class PanelCentralState {
    object Init : PanelCentralState()
    object Loading : PanelCentralState()
    object Success : PanelCentralState()
    data class Error(val message: String) : PanelCentralState()
}