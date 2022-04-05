package com.example.compose_workshop

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var clickData by rememberSaveable {
                    mutableStateOf(
                        ClickData(
                            clicks = 0,
                            time = "never"
                        )
                    )
                }

                val movies = remember {
                    mutableStateOf(
                        listOf(
                            Movie(
                                id = "1",
                                title = "My movie",
                                date = LocalDate.now()
                            )
                        )
                    )
                }

                val scope = rememberCoroutineScope()

                Column {
                    Grid()

                    Chessboard(1 to 1)

                    ClickCount(
                        clickData = clickData,
                        onClick = {
                            clickData = ClickData(
                                clicks = clickData.clicks + 1,
                                time = Date().toString()
                            )
                        }
                    )

                    MovieList(
                        list = movies.value,
                        onAddClicked = {
                            scope.launch {
                                addNewMovie(movies)
                            }
                        }
                    )

                    LaunchedEffect(movies.value) {
                        logMoviesToAnalytics(movies.value)
                    }

                    DisposableEffect(Unit) {
                        val originalMode = window.attributes.softInputMode
                        window.setSoftInputMode(SOFT_INPUT_ADJUST_PAN)

                        onDispose {
                            window.setSoftInputMode(originalMode)
                        }
                    }

                }
            }
        }
    }
}

private suspend fun addNewMovie(movies: MutableState<List<Movie>>) = withContext(Dispatchers.Default) {
    val oldMovies = movies.value
    movies.value = buildList {
        add(
            Movie(
                id = (oldMovies.size + 1).toString(),
                title = "My movie",
                date = LocalDate.now()
            )
        )
        addAll(oldMovies)
    }
}

private suspend fun logMoviesToAnalytics(list: List<Movie>) {
}

@Composable
fun ClickCount(
    clickData: ClickData,
    onClick: () -> Unit
) {
    Column {
        Text(text = "Clicked count: ${clickData.clicks}")
        Text(text = "Last clicked: ${clickData.time}")

        Button(onClick = onClick) {
            Text("Click me")
        }
    }
}

@Composable
fun MovieList(
    list: List<Movie>,
    onAddClicked: () -> Unit
) {
    Column {
        Button(onClick = onAddClicked) {
            Text(text = "Add a Movie")
        }

        for (movie in list) {
            key(movie.id) { // with key() old views are cached and don't need to calculate at recompose
                MovieOverview(movie = movie)
            }
        }
    }
}

@Composable
fun MovieOverview(movie: Movie) {
    Text(text = movie.title)
    Log.i("MovieOverview", movie.id)
}

@Composable
fun Grid() {
    for (i in 0..7) {
        Row() {
            for (j in 0..7) {
                Column() {
                    Text(text = "${i + 1}, ${j + 1}")
                }
            }
        }
    }
}

@Composable
fun Chessboard(knightDefaultPosition: Pair<Int, Int>) {
    var knightPosition by remember {
        mutableStateOf<Pair<Int, Int>?>(knightDefaultPosition.first to knightDefaultPosition.second)
    }

    Row {
        repeat(8) { columnIndex ->
            Column(modifier = Modifier.weight(1f)) {
                repeat(8) { rowIndex ->
                    val isKnightCell = columnIndex == knightPosition?.first &&
                        rowIndex == knightPosition?.second

                    Cell(getCellColor(rowIndex, columnIndex), isKnightCell) {
                        knightPosition = if (isKnightCell) {
                            null
                        } else {
                            columnIndex to rowIndex
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Cell(
    color: Color,
    isKnightCell: Boolean,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .aspectRatio(1f)
            .background(color)
            .clickable { onClick() }
    ) {
        if (isKnightCell) {
            Image(
                painter = painterResource(id = R.drawable.knight),
                contentDescription = "Knight",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

private fun getCellColor(rowIndex: Int, columnIndex: Int) = if (isLightCell(rowIndex, columnIndex)) {
    Color.White
} else {
    Color.Black
}

private fun isLightCell(rowIndex: Int, columnIndex: Int) =
    (rowIndex.mod(2) == 0) == (columnIndex.mod(2) == 0)

@Parcelize
data class ClickData(
    val clicks: Int,
    val time: String
) : Parcelable

@Immutable
data class Movie(
    val id: String,
    val title: String,
    val date: LocalDate
)

@Preview
@Composable
fun ChessboardPreview(
    @PreviewParameter(ChessboardPreviewParameterProvider::class) knightPosition: Pair<Int, Int>
) {
    Chessboard(knightPosition)
}

class ChessboardPreviewParameterProvider : PreviewParameterProvider<Pair<Int, Int>> {
    override val values: Sequence<Pair<Int, Int>>
        get() = sequenceOf(
            0 to 3,
            3 to 7,
            7 to 5
        )
}
