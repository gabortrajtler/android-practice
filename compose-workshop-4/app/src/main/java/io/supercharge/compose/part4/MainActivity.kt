package io.supercharge.compose.part4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Greeting(name = "Android")

                var sliderPosition by remember { mutableStateOf(0f) }

                Column(Modifier.fillMaxSize()) {
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it }
                    )

                    Box(modifier = Modifier
                        .offset {
                            IntOffset(
                                x = (sliderPosition * 200.dp.roundToPx()).toInt(),
                                y = (sliderPosition * 200.dp.roundToPx()).toInt()
                            )
                        }
                        .size(20.dp)
                        .background(Color.Black)
                    )

                    CustomLayoutColumn {
                        Text(text = "First")
                        Text(text = "Second")
                    }
                }

                ResponsiveBox()

                DrawCircle()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun CustomLayoutColumn(content: @Composable () -> Unit) {
    Layout(content = content) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun ResponsiveBox() {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        if (maxWidth < maxHeight) {
            Column {
                Text(text = "Kicsi")
                Text(text = "a szélesség")
            }
        } else {
            Row {
                Column {
                    Text(text = "Nagy")
                    Text(text = "a szélesség")
                }
                Text(text = "Nagy")
                Text(text = "a tét")
            }
        }
    }
}

@Composable
private fun DrawCircle() {
    var color: Color = Color.Blue

    Canvas(modifier = Modifier
        .fillMaxSize()
        .clickable {
            color = listOf(Color.Blue, Color.Black, Color.Cyan).random()
        }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawCircle(
            color = color,
            style = Stroke(width = 200F)
        )

        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = color,
            strokeWidth = 200F
        )
    }
}