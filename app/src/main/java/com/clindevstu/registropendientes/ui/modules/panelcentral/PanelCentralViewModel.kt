package com.clindevstu.registropendientes.ui.modules.panelcentral

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.clindevstu.registropendientes.ui.modules.splashprincipal.SplashPrincipalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PanelCentralViewModel @Inject constructor(
    application: Application
) : AndroidViewModel (application) {
    private val _state = MutableStateFlow<PanelCentralState>(PanelCentralState.Init)
    val state: StateFlow<PanelCentralState> = _state

}