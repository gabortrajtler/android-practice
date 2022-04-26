package io.supercharge.compose.part5

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.supercharge.compose.part5.PickedDateKey
import org.junit.Rule
import org.junit.Test

class PropertiesTest {

    @get:Rule
    val activityTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAssertions() {
        activityTestRule.onNode(SemanticsMatcher.expectValue(PickedDateKey, 1445378400))
            .assertExists()
    }
}
