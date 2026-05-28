package com.ardakazanci.progsettings.screen

import androidx.lifecycle.ViewModel
import com.ardakazanci.progsettings.model.AirConditionerState
import com.ardakazanci.progsettings.model.ClimateMode
import com.ardakazanci.progsettings.model.FanSpeed
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

    fun updateMode(mode: ClimateMode) {
        _state.update { it.copy(mode = mode) }
    }

    fun updateFanSpeed(fanSpeed: FanSpeed) {
        _state.update { it.copy(fanSpeed = fanSpeed) }
    }

    fun toggleEcoMode() {
        _state.update { it.copy(isEcoMode = !it.isEcoMode) }
    }
}
