package io.supercharge.compose.part5

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import org.junit.Test

class ExampleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testIfItWorks() {
        composeTestRule.setContent {
            MaterialTheme {
                Text(text = "Hello Android!")
            }
        }

        composeTestRule.onRoot().printToLog("ExampleTest")
    }
}
