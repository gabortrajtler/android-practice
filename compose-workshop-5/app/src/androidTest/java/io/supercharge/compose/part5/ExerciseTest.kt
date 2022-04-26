package io.supercharge.compose.part5

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class ExerciseTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testFirstExercise() {
        // Given: a composable with a button and a text
        composeTestRule.setContent {
            MaterialTheme {
                var visible by remember { mutableStateOf(false) }

                Column {
                    Button(onClick = { visible = true }) {

                    }

                    if (visible) {
                        Text(text = "text")
                    }
                }
            }
        }

        // Given: the text is hidden by default
        composeTestRule.onNodeWithText("text").assertDoesNotExist()

        // When: you click on the button
        composeTestRule.onNode(hasClickAction()).performClick()

        // Then: the text should be visible
        composeTestRule.onNodeWithText("text")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSecondExercise() {
        composeTestRule.mainClock.autoAdvance = false

        // Given: a counter state
        val counter = mutableStateOf(0)

        // Given: a composable with a button and a text
        composeTestRule.setContent {
            Column {
                Button(onClick = {
                    counter.value = counter.value + 1
                }) {

                }

                Text(text = counter.value.toString())
            }
        }

        // When: you click on the button
        composeTestRule.onNode(hasClickAction()).performClick()

        composeTestRule.mainClock.advanceTimeBy(1000)

        // Then: the counter should be incremented
        assertThat(counter.value).isEqualTo(1)

        // Then: the counter should be incremented
        composeTestRule.onNodeWithText("1").assertExists()
    }
}
