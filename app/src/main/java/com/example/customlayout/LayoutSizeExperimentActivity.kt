package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
            var parentSize by remember { mutableStateOf(150) }
            Text(text = "Parent's Size: $parentSize")
            Slider(
                value = parentSize.toFloat(),
                onValueChange = { parentSize = it.toInt() },
                valueRange = 0f..300f
            )
            var layoutSize by remember { mutableStateOf(150) }
            Text(text = "Layout's Size: $layoutSize")
            Slider(
                value = layoutSize.toFloat(),
                onValueChange = { layoutSize = it.toInt() },
                valueRange = 0f..300f
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(parentSize.dp)
                        .border(1.dp, Color.Green)
                ) {
                    val textModifier = Modifier.border(1.dp, Color.Blue)
                    CustomLayout(layoutSize.dp, Modifier.size(parentSize.dp)) {
                        Text("Hello There", modifier = textModifier)
                        Text("Good", modifier = textModifier)
                        Text("Longer please", modifier = textModifier)
                        Text("More Text", modifier = textModifier)
                    }
                }
            }
        }

    }

    @Composable
    fun CustomLayout(
        layoutSize: Dp,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Layout(
            modifier = modifier.border(2.dp, Color.Red),
            content = content
        ) { measurables, constraints ->
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0,
            )
            val placaebles = measurables.map { measurable ->
                measurable.measure(constraints = looseConstraints)
            }

            layout(layoutSize.roundToPx(), layoutSize.roundToPx()) {
                var y = 0

                placaebles.forEach { placeable ->
                    placeable.place(0, y)
                    y += placeable.height
                }
            }
        }
    }
}
