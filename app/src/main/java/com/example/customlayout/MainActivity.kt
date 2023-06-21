package com.example.customlayout

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.customlayout.ui.theme.CustomLayoutTheme
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
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
    private fun Greeting(modifier: Modifier = Modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ButtonLauncher(AutoWidthSortColumnActivity::class)
            ButtonLauncher(LayoutSizeExperimentActivity::class)
            ButtonLauncher(LayoutModifierSizeExperimentActivity::class)
            ButtonLauncher(DividerColumnExperimentActivity::class)
            ButtonLauncher(DividerLazyColumnExperimentActivity::class)
            ButtonLauncher(NegativePaddingExperimentActivity::class)
        }
    }

    @Composable
    fun ButtonLauncher(kclass: KClass<*>) {
        Button(onClick = {
            startActivity(Intent(this, kclass.java))
        }) {
            Text(kclass.simpleName.toString())
        }
    }
}


