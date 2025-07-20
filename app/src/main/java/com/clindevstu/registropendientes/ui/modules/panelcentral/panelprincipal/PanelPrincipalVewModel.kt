package com.clindevstu.registropendientes.ui.modules.panelcentral.panelprincipal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PanelPrincipalVewModel @Inject constructor(
    application: Application
):   AndroidViewModel(application){
    private val _state = MutableStateFlow<PanelPrincipalState>(PanelPrincipalState.Init)
    val state: StateFlow<PanelPrincipalState> = _state



}