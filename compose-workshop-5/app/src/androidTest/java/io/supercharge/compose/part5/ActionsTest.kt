package io.supercharge.compose.part5

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import org.junit.Rule
import org.junit.Test

class ActionsTest {

    @get:Rule
    val activityTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAssertions() {
        activityTestRule.onNode(hasText("Button")).performClick()
        activityTestRule.onNode(hasText("Button")).performSemanticsAction(SemanticsActions.OnClick)
        // activityTestRule.onNode(hasText("Button")).performKeyPress(KeyEvent())
        activityTestRule.onNode(hasText("Button")).performTouchInput {
            click()
            advanceEventTime(100)
            swipeUp()
        }
    }
}
