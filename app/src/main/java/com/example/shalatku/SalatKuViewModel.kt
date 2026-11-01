package com.example.shalatku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SalatKuViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SalatKuUiState())
    val uiState: StateFlow<SalatKuUiState> = _uiState.asStateFlow()

    // Penghitung pemanggilan internal (bukan UI state)
    private var loadCallCount = 0

    fun togglePrayer(name: String) {
        _uiState.update { state ->
            val done = if (name in state.donePrayers) {
                state.donePrayers - name
            } else {
                state.donePrayers + name
            }
            state.copy(donePrayers = done)
        }
    }

    fun clearPrayers() {
        _uiState.update { it.copy(donePrayers = emptyList()) }
    }

    fun selectDay(index: Int) {
        _uiState.update { it.copy(selectedDay = index) }
    }

    fun incrementTasbih() {
        _uiState.update { state ->
            val next = state.tasbihCount + 1
            if (next == state.roundSize) {
                state.copy(tasbihCount = 0, completedRounds = state.completedRounds + 1)
            } else {
                state.copy(tasbihCount = next)
            }
        }
    }

    fun resetTasbih() {
        _uiState.update { it.copy(tasbihCount = 0, completedRounds = 0) }
    }

    fun setRoundSize(size: Int) {
        _uiState.update { it.copy(roundSize = size, tasbihCount = 0) }
    }

    // HW13: Simulasi pemuatan jadwal shalat (panggilan genap gagal, ganjil berhasil)
    fun loadPrayerTimes() {
        if (_uiState.value.prayerTimes is PrayerTimesUiState.Loading) return

        loadCallCount++
        val currentCall = loadCallCount

        viewModelScope.launch {
            _uiState.update { it.copy(prayerTimes = PrayerTimesUiState.Loading) }
            delay(1500)
            val nextState = if (currentCall % 2 == 0) {
                PrayerTimesUiState.Error("Failed to load prayer times. Check your connection.")
            } else {
                PrayerTimesUiState.Success(samplePrayers)
            }
            _uiState.update { it.copy(prayerTimes = nextState) }
        }
    }
}