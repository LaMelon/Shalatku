package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrayerTimesSection(
    state: PrayerTimesUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        when (state) {
            PrayerTimesUiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(8.dp))
                    Text("Loading prayer times...")
                }
            }
            is PrayerTimesUiState.Success -> {
                Button(onClick = onRefresh) {
                    Text("Refresh")
                }
                Spacer(Modifier.height(8.dp))
                state.times.forEach { prayer ->
                    PrayerCard(prayer)
                }
            }
            is PrayerTimesUiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = onRefresh) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun PrayerTimesSectionLoadingPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Loading,
        onRefresh = {}
    )
}

@Preview(showBackground = true, name = "Success State")
@Composable
fun PrayerTimesSectionSuccessPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Success(samplePrayers),
        onRefresh = {}
    )
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun PrayerTimesSectionErrorPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Error("Failed to load prayer times."),
        onRefresh = {}
    )
}