package io.supercharge.compose.part5

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class FindersTest {

    @get:Rule
    val activityTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun tesFindNodes() {
        // select a single node
        activityTestRule.onNode(hasText("Button"), useUnmergedTree = true)
        activityTestRule.onNode(hasText("Button"), useUnmergedTree = false)

        // select multiple node
        activityTestRule.onAllNodes(hasText("Button"), useUnmergedTree = true)
        activityTestRule.onAllNodes(hasText("Button"), useUnmergedTree = false)

        // convenience finders
        activityTestRule.onNodeWithText("Text")
        activityTestRule.onNodeWithContentDescription("ContentDescription")
        activityTestRule.onNodeWithTag("Tag")
    }
}
