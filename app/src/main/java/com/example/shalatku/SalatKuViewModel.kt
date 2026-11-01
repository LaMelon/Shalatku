package com.example.shalatku

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SalatKuViewModel : ViewModel() {

    // --- HW12 Part B: Tasbih Target ---
    var tasbihCount by mutableIntStateOf(0)
        private set

    var completedRounds by mutableIntStateOf(0)
        private set

    var roundSize by mutableIntStateOf(99)
        @JvmName("updateRoundSizeProperty")
        private set

    // --- Tracker ---
    private val _donePrayers = mutableStateListOf<String>()
    val donePrayers: List<String> get() = _donePrayers

    // --- Home ---
    var selectedDay by mutableIntStateOf(0)
        private set

    // Logika Increment Tasbih dengan Target Dinamis
    fun incrementTasbih() {
        tasbihCount++
        if (tasbihCount == roundSize) {
            completedRounds++
            tasbihCount = 0
        }
    }

    fun resetTasbih() {
        tasbihCount = 0
        completedRounds = 0
    }

    // HW12 Part B: Mengubah ukuran target (mereset count, menjaga completedRounds)
    fun setRoundSize(size: Int) {
        roundSize = size
        tasbihCount = 0
    }

    // Tracker Functions
    fun togglePrayer(name: String) {
        if (name in _donePrayers) _donePrayers.remove(name) else _donePrayers.add(name)
    }

    // HW12 Part A: Clear All Prayers
    fun clearPrayers() {
        _donePrayers.clear()
    }

    fun selectDay(index: Int) {
        selectedDay = index
    }
}