package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.example.customlayout.ui.theme.CustomLayoutTheme

class NegativePaddingExperimentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }

    @Composable
    fun Greeting() {
        val density = LocalDensity.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val textWidth = 180.dp
            var parentWidth by remember { mutableStateOf(300) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Parent Width: $parentWidth", modifier = Modifier.width(textWidth))
                Slider(
                    value = parentWidth.toFloat(),
                    onValueChange = { parentWidth = it.toInt() },
                    valueRange = 0f..300f
                )
            }
            var parentPadding by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Parent Padding: $parentPadding", modifier = Modifier.width(textWidth))
                Slider(
                    value = parentPadding.toFloat(),
                    onValueChange = { parentPadding = it.toInt() },
                    valueRange = 0f..64f
                )
            }
            var dividersPadding by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Dividers' Padding: $dividersPadding",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = dividersPadding.toFloat(),
                    onValueChange = { dividersPadding = it.toInt() },
                    valueRange = -150f..150f
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .width(parentWidth.dp)
                    .fillMaxHeight()
                    .background(Color.Yellow)
                    .padding(parentPadding.dp)
            ) {
                Text("0. Reference Divider")
                Divider(modifier = Modifier.height(20.dp))
                Text("1. OffSet Padding")
                Divider(modifier = Modifier.height((20.dp)).offset(dividersPadding.dp))
                Text("2. Required Width Padding")
                Divider(modifier = Modifier.height(20.dp).requiredWidth(
                            width = (parentWidth.dp - parentPadding.dp * 2 - dividersPadding.dp * 2),
                ))
                Text("3. Layout Constraint Only Padding")
                Divider(modifier = Modifier
                    .height(20.dp)
                    .layout { measurable, constraints ->
                        val placeable =
                            measurable.measure(constraints.offset((-dividersPadding * 2).dp.roundToPx()))
                        layout(
                            placeable.width,
                            placeable.height
                        ) { placeable.place(0, 0) }
                    }
                )
                Text("4. Layout Managed Padding")
                Divider(modifier = Modifier
                    .height(20.dp)
                    .layout { measurable, constraints ->
                        val placeable =
                            measurable.measure(constraints.offset((-dividersPadding * 2).dp.roundToPx()))
                        layout(
                            placeable.width + (dividersPadding * 2).dp.roundToPx(),
                            placeable.height
                        ) { placeable.place(0 + dividersPadding.dp.roundToPx(), 0) }
                    }
                )
            }
        }
    }
}
