package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.example.customlayout.ui.theme.CustomLayoutTheme

class LayoutModifierSizeExperimentActivity : ComponentActivity() {
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
            var layoutSizeChange by remember { mutableStateOf(0) }
            Text(text = "Layout's Size Change: $layoutSizeChange")
            Slider(
                value = layoutSizeChange.toFloat(),
                onValueChange = { layoutSizeChange = it.toInt() },
                valueRange = -150f..150f
            )
            var constraintOffSet by remember { mutableStateOf(0) }
            Text(text = "Constraint Offset: $constraintOffSet")
            Slider(
                value = constraintOffSet.toFloat(),
                onValueChange = { constraintOffSet = it.toInt() },
                valueRange = -150f..150f
            )
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                val size = parentSize.dp
                BoxLayout(size, Color.Red, layoutSizeChange.dp, constraintOffSet.dp) {
                    BoxLayout(size, Color.Green, layoutSizeChange.dp, constraintOffSet.dp) {
                        BoxLayout(size, Color.Blue, layoutSizeChange.dp, constraintOffSet.dp) {
                            BoxLayout(size, Color.Magenta, layoutSizeChange.dp, constraintOffSet.dp) {
                                Text("I am here")
                            }
                        }
                    }
                }
            }
        }

    }

    @Composable
    private fun BoxLayout(
        size: Dp,
        borderColor: Color,
        layoutSizeChange: Dp,
        constraintOffSet: Dp,
        content: @Composable BoxScope.() -> Unit = {}
    ) {
        Box(modifier = Modifier
            .width(size)
            .height(size)
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints.offset(
                    constraintOffSet.roundToPx(),
                    constraintOffSet.roundToPx()
                ))
                layout(
                    placeable.width + layoutSizeChange.roundToPx(),
                    placeable.height + layoutSizeChange.roundToPx()
                ) {
                    placeable.place(0, 0)
                }
            }
            .border(1.dp, borderColor), content = content)
    }
}
