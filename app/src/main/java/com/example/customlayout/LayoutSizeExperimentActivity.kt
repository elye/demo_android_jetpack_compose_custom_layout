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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
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
    fun Greeting(modifier: Modifier = Modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(200.dp).border(3.dp, Color.Green)
            ) {
                val textModifier = Modifier.border(1.dp, Color.Blue)
                CustomLayout(Modifier.size(150.dp)) {
                    Text("Hello There", modifier = textModifier)
                    Text("Good", modifier = textModifier)
                    Text("Longer please", modifier = textModifier)
                    Text("More Text", modifier = textModifier)
                }
            }
        }
    }

    @Composable
    fun CustomLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
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
            }.sortedBy { it.width }

            layout(150.dp.roundToPx(), 150.dp.roundToPx()) {
                var y = 0

                placaebles.forEach { placeable ->
                    placeable.place(0, y)
                    y += placeable.height
                }
            }
        }
    }
}



