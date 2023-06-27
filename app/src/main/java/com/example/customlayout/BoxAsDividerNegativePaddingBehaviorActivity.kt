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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.example.customlayout.ui.theme.CustomLayoutTheme

class BoxAsDividerNegativePaddingBehaviorActivity :ComponentActivity() {
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Greeting() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val textWidth = 180.dp
            var containerSize by remember { mutableStateOf(150) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Container Size: $containerSize", modifier = Modifier.width(textWidth))
                Slider(
                    value = containerSize.toFloat(),
                    onValueChange = { containerSize = it.toInt() },
                    valueRange = 0f..300f
                )
            }
            var boxSize by remember { mutableStateOf(150) }
            var useBoxSize by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(modifier = Modifier.width(textWidth),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = useBoxSize,
                        onCheckedChange = { useBoxSize = it }
                    )
                    Text(text = "Box Size: $boxSize")
                }
                Slider(
                    value = boxSize.toFloat(),
                    onValueChange = { boxSize = it.toInt() },
                    valueRange = 0f..300f,
                    enabled = useBoxSize
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
            var layoutSizeChange by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Layout's Change: $layoutSizeChange",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = layoutSizeChange.toFloat(),
                    onValueChange = { layoutSizeChange = it.toInt() },
                    valueRange = -200f..200f
                )
            }
            var placement by remember { mutableStateOf(0) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Placement: $placement", modifier = Modifier.width(textWidth))
                Slider(
                    value = placement.toFloat(),
                    onValueChange = { placement = it.toInt() },
                    valueRange = -200f..200f
                )
            }
            var verticalCentered by remember { mutableStateOf(false) }
            var horizontalCentered by remember { mutableStateOf(false) }
            Row {
                Row(modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = verticalCentered,
                        onCheckedChange = { verticalCentered = it }
                    )
                    Text("Vertical Centered")
                }
                Row(modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = horizontalCentered,
                        onCheckedChange = { horizontalCentered = it }
                    )
                    Text("Horizontal Centered")
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                val horizontalAlignment = if (verticalCentered)
                    Alignment.CenterHorizontally else Alignment.Start
                val verticalArrangement = if (horizontalCentered)
                    Arrangement.Center else Arrangement.Top
                Column(
                    horizontalAlignment = horizontalAlignment,
                    verticalArrangement = verticalArrangement,
                    modifier = Modifier
                        .size(containerSize.dp)
                        .background(Color.Yellow)
                ) {
                    BoxLayout(
                        constraintOffSet.dp,
                        layoutSizeChange.dp,
                        placement.dp,
                        boxSize.dp,
                        useBoxSize
                    ) {
                        Box(modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }

    @Composable
    private fun BoxLayout(
        constraintOffSet: Dp,
        layoutSizeChange: Dp,
        placement: Dp,
        boxSize: Dp,
        useBoxSize: Boolean,
        content: @Composable BoxScope.() -> Unit = {}
    ) {
        Box(modifier = Modifier
            .conditional(useBoxSize) {
                size(boxSize)
            }
            .background(GrayAlpha)
            .layout { measurable, constraints ->
                // Measure
                val placeable = measurable.measure(
                    constraints.offset(
                        constraintOffSet.roundToPx(),
                        constraintOffSet.roundToPx()
                    )
                )

                // Layout
                layout(
                    placeable.width + layoutSizeChange.roundToPx(),
                    placeable.height + layoutSizeChange.roundToPx()
                ) {
                    placeable.place(placement.roundToPx(), placement.roundToPx())
                }
            }
            .border(1.dp, Color.Red),
            content = content
        )
    }
}
