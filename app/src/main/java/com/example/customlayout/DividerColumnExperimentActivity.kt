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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.example.customlayout.ui.theme.CustomLayoutTheme

class DividerColumnExperimentActivity : ComponentActivity() {
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val textWidth = 180.dp
            var parentPadding by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Parent Padding: $parentPadding", modifier = Modifier.width(textWidth))
                Slider(
                    value = parentPadding.toFloat(),
                    onValueChange = { parentPadding = it.toInt() },
                    valueRange = 0f..64f
                )
            }
            var parentWidth by remember { mutableStateOf(150) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Parent Width: $parentWidth", modifier = Modifier.width(textWidth))
                Slider(
                    value = parentWidth.toFloat(),
                    onValueChange = { parentWidth = it.toInt() },
                    valueRange = 0f..300f
                )
            }
            var layoutSizeChange by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Layout's Change: $layoutSizeChange",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = layoutSizeChange.toFloat(),
                    onValueChange = { layoutSizeChange = it.toInt() },
                    valueRange = -250f..250f
                )
            }
            var constraintOffSet by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Constraint Offset: $constraintOffSet",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = constraintOffSet.toFloat(),
                    onValueChange = { constraintOffSet = it.toInt() },
                    valueRange = -250f..250f
                )
            }
            var placementX by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Placement X: $placementX", modifier = Modifier.width(textWidth))
                Slider(
                    value = placementX.toFloat(),
                    onValueChange = { placementX = it.toInt() },
                    valueRange = -150f..150f
                )
            }
            var placementY by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Placement Y: $placementY", modifier = Modifier.width(textWidth))
                Slider(
                    value = placementY.toFloat(),
                    onValueChange = { placementY = it.toInt() },
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
                Divider(modifier = Modifier.height(20.dp))
                Divider(modifier = Modifier
                    .height(20.dp)
                    .layout { measurable, constraints ->
                        val placeable =
                            measurable.measure(constraints.offset(constraintOffSet.dp.roundToPx()))
                        layout(
                            placeable.width + layoutSizeChange.dp.roundToPx(),
                            placeable.height
                        ) { placeable.place(placementX, placementY) }
                    }
                )
            }
        }
    }
}
