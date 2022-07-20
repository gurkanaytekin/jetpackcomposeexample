package com.gurkanaytekin.jetpackcomposeexample.tests.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.text.input.KeyboardType
import com.gurkanaytekin.jetpackcomposeexample.components.OutlinedTextFieldComposable
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

class ProjectTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun textFieldTest() {
        // Start the app
        composeTestRule.setContent {
            var value by mutableStateOf("gurkanaytekin@yandex.com.tr")
            OutlinedTextFieldComposable(value, { value = it }, "label", leadingIcon = Icons.Filled.Email, keyboardType = KeyboardType.Email )
        }

        composeTestRule.onNodeWithTag("Outlined")
    }
}