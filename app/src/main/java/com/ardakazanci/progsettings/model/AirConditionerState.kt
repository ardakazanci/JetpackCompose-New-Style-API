package com.ardakazanci.progsettings.model

enum class ClimateMode(val label: String) {
    Cool("Cool"),
    Heat("Heat"),
    Auto("Auto"),
    Fan("Fan"),
    Dry("Dry")
}

enum class FanSpeed(val label: String) {
    Low("Low"),
    Medium("Med"),
    High("High"),
    Auto("Auto")
}

data class AirConditionerState(
    val deviceName: String = "Smart Air Conditioner TD",
    val isOn: Boolean = true,
    val mode: ClimateMode = ClimateMode.Cool,
    val fanSpeed: FanSpeed = FanSpeed.Auto,
    val isEcoMode: Boolean = true,
    val powerUsageKwh: Int = 102,
    val timeUsageHours: Int = 3,
    val timeUsageMinutes: Int = 23,
    val currentTemperature: Float = 22f,
    val targetTemperature: Float = 22f,
    val minTemperature: Float = 16f,
    val maxTemperature: Float = 32f
) {
    val temperatureProgress: Float
        get() = (targetTemperature - minTemperature) / (maxTemperature - minTemperature)

    val formattedTimeUsage: String
        get() = "${timeUsageHours}h ${timeUsageMinutes}m"
}
