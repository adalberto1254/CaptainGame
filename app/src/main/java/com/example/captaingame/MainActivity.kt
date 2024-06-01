package com.example.captaingame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                CaptainGame()
            }
        }
    }
}

@Composable
fun CaptainGame() {
    val treasuresFound =
        remember {
            mutableStateOf(0)
        }
    val direction =
        remember {
            mutableStateOf("North")
        }
    val treasureMessage =
        remember {
            mutableStateOf("You are the captain of a ship.\nSail in any direction to find treasure!")
        }

    Column(
        modifier =
        Modifier
            .padding(top = 300.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Treasures Found: ${treasuresFound.value}")
        Text("Current Direction: ${direction.value}")
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = treasureMessage.value, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(32.dp))
        DirectionalButtons(treasuresFound, direction, treasureMessage)
    }
}

@Composable
fun DirectionalButtons(
    treasuresFound: MutableState<Int>,
    direction: MutableState<String>,
    treasureMessage: MutableState<String>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            checkIfTreasureFound(treasuresFound, treasureMessage)
            direction.value = "North"
        }) {
            Text("Sail North")
        }
        Row {
            Button(onClick = {
                checkIfTreasureFound(treasuresFound, treasureMessage)
                direction.value = "West"
            }) {
                Text("Sail West")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                checkIfTreasureFound(treasuresFound, treasureMessage)
                direction.value = "East"
            }) {
                Text("Sail East")
            }
        }
        Button(onClick = {
            checkIfTreasureFound(treasuresFound, treasureMessage)
            direction.value = "South"
        }) {
            Text("Sail South")
        }
    }
}

fun checkIfTreasureFound(
    treasuresFound: MutableState<Int>,
    treasureMessage: MutableState<String>,
) {
    val foundtreasure = "You found a treasure!"
    val notreasure = "You found nothing. Keep sailing!"
    return if (Random.nextBoolean()) {
        treasuresFound.value += 1
        treasureMessage.value = foundtreasure
    } else {
        treasureMessage.value = notreasure
    }
}
