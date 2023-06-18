package com.example.customlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.example.customlayout.ui.theme.CustomLayoutTheme

class AutoWidthSortColumnActivity : ComponentActivity() {
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
            CustomLayout(Modifier.size(150.dp).border(1.dp, Color.Green)) {
                Text("Hello There")
                Text("Good")
                Text("Longer please")
                Text("More Text")
                Box(Modifier.height(10.dp).width(100.dp).background(Color.Cyan))
                Box(Modifier.height(10.dp).width(10.dp).background(Color.Red))
                Box(Modifier.height(10.dp).width(50.dp).background(Color.Magenta))
            }
        }
    }

    @Composable
    fun CustomLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
        Layout(
            modifier = modifier,
            content = content
        ) { measurables, constraints ->
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0,
            )
            val placaebles = measurables.map { measurable ->
                measurable.measure(constraints = looseConstraints)
            }.sortedBy { it.width }

            layout(constraints.maxWidth, constraints.maxHeight) {
                var y = 0
                placaebles.forEach { placeable ->
                    placeable.place(0, y)
                    y += placeable.height
                }
            }
        }
    }
}
