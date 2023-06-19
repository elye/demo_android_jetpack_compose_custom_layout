package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.example.customlayout.ui.theme.CustomLayoutTheme

class LayoutSizeExperimentActivity : ComponentActivity() {
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
            var boxSize by remember { mutableStateOf(150) }
            var parentSizeX by remember { mutableStateOf(0.9f) }
            var parentSizeY by remember { mutableStateOf(0.9f) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "X Ratio: ${parentSizeX.format(2)}",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = parentSizeX,
                    onValueChange = { parentSizeX = it },
                    valueRange = 0f..1f
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Y Ratio ${parentSizeY.format(2)}",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = parentSizeY,
                    onValueChange = { parentSizeY = it },
                    valueRange = 0f..1f
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Size: $boxSize", modifier = Modifier.width(textWidth))
                Slider(
                    value = boxSize.toFloat(),
                    onValueChange = { boxSize = it.toInt() },
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
                    valueRange = -150f..150f
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
                    valueRange = -150f..150f
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(parentSizeX)
                        .fillMaxHeight(parentSizeY)
                        .background(Color.LightGray)
                ) {
                    val size = boxSize.dp
                    BoxLayout(
                        size,
                        Color.Red,
                        layoutSizeChange.dp,
                        constraintOffSet.dp,
                        placementX.dp,
                        placementY.dp
                    ) {
                        BoxLayout(
                            size,
                            Color.Green,
                            layoutSizeChange.dp,
                            constraintOffSet.dp,
                            placementX.dp,
                            placementY.dp
                        ) {
                            BoxLayout(
                                size,
                                Color.Blue,
                                layoutSizeChange.dp,
                                constraintOffSet.dp,
                                placementX.dp,
                                placementY.dp
                            ) {
                                BoxLayout(
                                    size,
                                    Color.Magenta,
                                    layoutSizeChange.dp,
                                    constraintOffSet.dp,
                                    placementX.dp,
                                    placementY.dp
                                ) {
                                    val textModifier = Modifier.border(1.dp, Color.Blue)
                                    Text("Hello There", modifier = textModifier)
                                    Text("Something Here", modifier = textModifier)
                                    Text("Test it one", modifier = textModifier)
                                    Text("Sometimes", modifier = textModifier)
                                    Text("Short", modifier = textModifier)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BoxLayout(
        size: Dp,
        borderColor: Color,
        layoutSizeChange: Dp,
        constraintOffSet: Dp,
        placementX: Dp,
        placementY: Dp,
        content: @Composable () -> Unit = {}
    ) {
        Layout(
            modifier = Modifier
                .width(size)
                .height(size)
                .border(1.dp, borderColor),
            content = content
        ) { measurables, constraints ->
            val placaebles = measurables.map { measurable ->
                val looseConstraints = constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                )
                measurable.measure(constraints = looseConstraints.offset(
                    constraintOffSet.roundToPx(),
                    constraintOffSet.roundToPx()
                ))
            }

            layout(
                placaebles.maxOf { it.width } + layoutSizeChange.roundToPx(),
                placaebles.sumOf { it.height } + layoutSizeChange.roundToPx()
            )  {
                var y = placementY.roundToPx()
                placaebles.forEach { placeable ->
                    placeable.place(placementX.roundToPx(), y)
                    y += placeable.height
                }
            }
        }
    }
}
