package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TasbihScreen(
    count: Int,
    rounds: Int,
    roundSize: Int,
    onIncrement: () -> Unit,
    onReset: () -> Unit,
    onSetRoundSize: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tasbih", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))

        // HW12 Part B: Pilihan Target (33, 99, 100) tanpa logika 'if' di UI
        Row(horizontalArrangement = Arrangement.Center) {
            listOf(33, 99, 100).forEach { size ->
                FilterChip(
                    selected = roundSize == size,
                    onClick = { onSetRoundSize(size) },
                    label = { Text("$size") }
                )
                Spacer(Modifier.width(8.dp))
            }
        }

        Spacer(Modifier.height(8.dp))
        Text("Rounds: $rounds (Target: $roundSize)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(16.dp))
        Text("$count", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onIncrement) { Text("+1") }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onReset) { Text("Reset") }
    }
}