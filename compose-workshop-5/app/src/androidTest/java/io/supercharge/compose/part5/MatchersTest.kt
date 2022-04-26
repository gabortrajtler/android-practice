package io.supercharge.compose.part5

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import org.junit.Rule
import org.junit.Test

class MatchersTest {

    @get:Rule
    val activityTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun tesHierarchicalMatchers() {
        // matchers
        activityTestRule.onNode(hasParent(hasText("Button")))
        activityTestRule.onNode(hasAnySibling(hasText("Button")))
        activityTestRule.onNode(hasAnyAncestor(hasText("Button")))
        activityTestRule.onNode(hasAnyDescendant(hasText("Button")))

        // example usage
        activityTestRule.onNode(hasParent(hasText("Button")))
            .assertIsDisplayed()
    }

    @Test
    fun testSelectors() {
        activityTestRule.onNode(hasTestTag("Players"))
            .onChildren()
            .filter(hasClickAction())
            .assertCountEquals(4)
            .onFirst()
            .assert(hasText("John"))
    }
}



