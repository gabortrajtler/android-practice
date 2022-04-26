package io.supercharge.compose.part5

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class AssertionsTest {

    @get:Rule
    val activityTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAssertions() {
        // single assertion matcher
        activityTestRule.onNode(hasClickAction())
            .assert(hasText("Button"))

        // multiple assertion matcher
        activityTestRule.onNode(hasClickAction())
            .assert(hasText("Button") or hasText("Button2"))

        // other examples
        activityTestRule.onAllNodes(hasText("open", substring = true))
            .assertCountEquals(4)
        activityTestRule.onAllNodes(hasText("open", substring = true))
            .assertAny(hasTestTag("TestTag"))
        activityTestRule.onAllNodes(hasText("open", substring = true))
            .assertAll(hasClickAction())
    }
}
