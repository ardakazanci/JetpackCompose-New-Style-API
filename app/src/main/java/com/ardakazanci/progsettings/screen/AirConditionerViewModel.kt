package com.ardakazanci.progsettings.screen

import androidx.lifecycle.ViewModel
import com.ardakazanci.progsettings.model.AirConditionerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AirConditionerViewModel : ViewModel() {

    private val _state = MutableStateFlow(AirConditionerState())
    val state: StateFlow<AirConditionerState> = _state.asStateFlow()

    fun togglePower() {
        _state.update { it.copy(isOn = !it.isOn) }
    }

    fun updateTargetTemperature(temperature: Float) {
        _state.update {
            it.copy(
                targetTemperature = temperature.coerceIn(it.minTemperature, it.maxTemperature)
            )
        }
    }
}
