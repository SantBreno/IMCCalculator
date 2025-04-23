package com.example.imccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCApp()
        }
    }
}

@Composable
fun IMCApp() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("IMC Calculator", fontSize = 24.sp)

                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Type your weight (kg)") }
                )

                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Type your Height (cm)") }
                )

                Button(onClick = {
                    val w = weight.toFloatOrNull()
                    val heightCm = height.toFloatOrNull()
                    if (w != null && heightCm != null && heightCm > 0) {
                        val h = heightCm / 100
                        val imc = w / (h * h)
                        result = "Your IMC is %.2f".format(imc)
                    } else {
                        result = "Please enter a valid input."
                    }
                }) {
                    Text("Calculate IMC")
                }

                Text(result, fontSize = 20.sp)
            }

        }
    }

    
}

@Preview(showBackground = true, name = "IMC Preview")
@Composable
fun IMCAppPreview() {
    IMCApp()
}