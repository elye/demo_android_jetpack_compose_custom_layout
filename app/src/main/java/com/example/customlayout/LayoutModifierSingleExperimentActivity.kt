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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.customlayout.ui.theme.CustomLayoutTheme

class LayoutModifierSingleExperimentActivity : ComponentActivity() {
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
            var boxSize by remember { mutableStateOf(150) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Box Size: $boxSize", modifier = Modifier.width(textWidth))
                Slider(
                    value = boxSize.toFloat(),
                    onValueChange = { boxSize = it.toInt() },
                    valueRange = 0f..300f
                )
            }
            var minSize by remember { mutableStateOf(150) }
            var maxSize by remember { mutableStateOf(150) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Box Min Size: $minSize",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = minSize.toFloat(),
                    onValueChange = { minSize = it.toInt() },
                    valueRange = 0f..maxSize.toFloat()
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Box Max Size: $maxSize",
                    modifier = Modifier.width(textWidth)
                )
                Slider(
                    value = maxSize.toFloat(),
                    onValueChange = { maxSize = it.toInt() },
                    valueRange = minSize.toFloat()..300f
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
            var text by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                }
            )
            var fullWidthCheckedState by remember { mutableStateOf(false) }
            var fullHeightCheckedState by remember { mutableStateOf(false) }
            Row {
                Row(modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = fullWidthCheckedState,
                        onCheckedChange = { fullWidthCheckedState = it }
                    )
                    Text("Full Width")
                }
                Row(modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = fullHeightCheckedState,
                        onCheckedChange = { fullHeightCheckedState = it }
                    )
                    Text("Full Height")
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                BoxLayout(
                    boxSize.dp,
                    minSize.dp,
                    maxSize.dp,
                    layoutSizeChange.dp,
                    placementX.dp,
                    placementY.dp
                ) {
                    val modifier = Modifier
                        .conditional(fullWidthCheckedState) {
                            fillMaxWidth()
                        }
                        .conditional(fullHeightCheckedState) {
                            fillMaxHeight()
                        }
                    Text(text.text, modifier = modifier)
                }
            }
        }
    }

    @Composable
    private fun BoxLayout(
        size: Dp,
        minSize: Dp,
        maxSize: Dp,
        layoutSizeChange: Dp,
        placementX: Dp,
        placementY: Dp,
        content: @Composable BoxScope.() -> Unit = {}
    ) {
        Box(modifier = Modifier
            .size(size)
            .background(Color.LightGray)
            .layout { measurable, constraints ->
                // Measure
                val looseConstraints = constraints.copy(
                    minWidth = minSize.roundToPx(),
                    maxWidth = maxSize.roundToPx(),
                    minHeight = minSize.roundToPx(),
                    maxHeight = maxSize.roundToPx(),
                )
                val placeable = measurable.measure(looseConstraints)

                // Layout
                layout(
                    constraints.maxWidth + layoutSizeChange.roundToPx(),
                    constraints.maxHeight + layoutSizeChange.roundToPx()
                ) {
                    placeable.place(placementX.roundToPx(), placementY.roundToPx())
                }
            }
            .border(1.dp, Color.Red),
            content = content
        )
    }
}
