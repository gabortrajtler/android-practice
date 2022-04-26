package io.supercharge.compose.part5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                //High level APIs
                AnimatedVisibilityExample()
                AnimatedContentExample()
                CrossfadeExample()
                AnimateContentSizeExample()

                // Low level APIs
                AnimateAsStateExample()
                AnimatableExample()
                AnimationVectorExample(targetCount = 10)
                UpdateTransitionExample()
                RememberInfiniteTransitionExample()

                // For Testing
                CustomDatePicker()
            }
        }
    }
}

@Composable
fun AnimatedVisibilityExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var visible by remember { mutableStateOf(false) }

        Button(onClick = { visible = !visible }) {
            Text(text = "Toggle visibility")
        }

        AnimatedVisibility(visible = visible) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "hidden text"
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var count by remember { mutableStateOf(0) }

        Button(onClick = { count++ }) {
            Text(text = "Increment")
        }

        Spacer(modifier = Modifier.size(16.dp))

        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(text = "Count: $targetCount")
        }
    }
}

@Composable
fun CrossfadeExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var currentPage by remember { mutableStateOf("A") }

        Crossfade(targetState = currentPage) { screen ->
            when (screen) {
                "A" -> Text("Page A")
                "B" -> Text("Page B")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row {
            Button(onClick = { currentPage = "A" }) {
                Text(text = "Page A")
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(onClick = { currentPage = "B" }) {
                Text(text = "Page B")
            }
        }
    }
}

@Composable
fun AnimateContentSizeExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var message by remember { mutableStateOf("Hello") }

        Button(onClick = { message = "Hello\nAndroid!" }) {
            Text(text = "Update message")
        }

        Spacer(modifier = Modifier.size(16.dp))

        Box(
            modifier = Modifier
                .background(Color.Blue)
                .animateContentSize()
                .padding(16.dp)
        ) {
            Text(text = message)
        }
    }
}

@Composable
fun AnimateAsStateExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var visible by remember { mutableStateOf(false) }

        Button(onClick = { visible = !visible }) {
            Text(text = "Toggle visibility")
        }

        val alpha by animateFloatAsState(if (visible) 1f else 0.2f)

        Box(
            modifier = Modifier.fillMaxWidth()
                .alpha(alpha)
                .background(Color.Green)
                .padding(16.dp)
        ) {
            Text(text = "text")
        }
    }
}

@Composable
fun AnimatableExample() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var ok by remember { mutableStateOf(false) }

        Button(onClick = { ok = !ok }) {
            Text(text = "Toggle")
        }

        val color = remember { Animatable(Color.Gray) }

        LaunchedEffect(ok) {
            color.animateTo(if (ok) Color.Green else Color.Red)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color.value)
                .padding(16.dp)
        ) {
            Text(text = "text")
        }
    }
}

val IntToVector: TwoWayConverter<Int, AnimationVector1D> =
    TwoWayConverter({ AnimationVector1D(it.toFloat()) }, { it.value.toInt() })

@Composable
fun AnimationVectorExample(targetCount: Int) {
    val count = remember {
        Animatable(
            initialValue = 0,
            typeConverter = IntToVector
        )
    }

    LaunchedEffect(targetCount) {
        count.animateTo(targetCount)
    }
}

@Composable
fun RememberInfiniteTransitionExample() {
    val infiniteTransition = rememberInfiniteTransition()

    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )
    }
}

enum class BoxState {
    Collapsed,
    Expanded
}

@Composable
fun UpdateTransitionExample() {
    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(currentState)

    val size by transition.animateDp { state ->
        when (state) {
            BoxState.Collapsed -> 64.dp
            BoxState.Expanded -> 128.dp
        }
    }
    val color by transition.animateColor { state ->
        when (state) {
            BoxState.Collapsed -> Color.Gray
            BoxState.Expanded -> Color.Red
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Button(onClick = { currentState = BoxState.Expanded }) {
                Text(text = "Expand")
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(onClick = { currentState = BoxState.Collapsed }) {
                Text(text = "Collapse")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(size)
                .background(color = color)
                .padding(16.dp)
        ) {
            Text(text = "text")
        }
    }
}

val PickedDateKey = SemanticsPropertyKey<Long>("PickedDate")
var SemanticsPropertyReceiver.pickerDate by PickedDateKey

@Composable
fun CustomDatePicker() {
    val datePickerValue by remember { mutableStateOf(0L) }
    Box(modifier = Modifier.semantics { pickerDate = datePickerValue })
}

@Preview
@Composable
private fun AnimatedVisibilityExamplePreview() {
    MaterialTheme {
        AnimatedVisibilityExample()
    }
}

@Preview
@Composable
private fun AnimatedContentExamplePreview() {
    MaterialTheme {
        AnimatedContentExample()
    }
}

@Preview
@Composable
private fun CrossfadeExamplePreview() {
    MaterialTheme {
        CrossfadeExample()
    }
}

@Preview
@Composable
fun AnimateContentSizeExamplePreview() {
    MaterialTheme {
        AnimateContentSizeExample()
    }
}

@Preview
@Composable
fun AnimateAsStateExamplePreview() {
    MaterialTheme {
        AnimateAsStateExample()
    }
}

@Preview
@Composable
fun AnimatableExamplePreview() {
    MaterialTheme {
        AnimatableExample()
    }
}

@Preview
@Composable
private fun AnimationVectorExamplePreview() {
    MaterialTheme {
        AnimationVectorExample(10)
    }
}

@Preview
@Composable
fun RememberInfiniteTransitionExamplePreview() {
    MaterialTheme {
        RememberInfiniteTransitionExample()
    }
}

@Preview
@Composable
private fun UpdateTransitionExamplePreview() {
    MaterialTheme {
        UpdateTransitionExample()
    }
}
