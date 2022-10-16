package com.example.android.testing.espresso.BasicSample.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.testing.espresso.BasicSample.R
import com.example.android.testing.espresso.BasicSample.base.BaseTest
import org.junit.Assert
import org.junit.Test

class BasicNavigationTest : BaseTest() {

    companion object {

        val testData = listOf("This is a test", "012345", "!@#$%^&*()_+")
    }

    @Test
    fun verifyMainActivityTest() {

        for (s in testData) {

            // Ensure the device is in Portrait
            Assert.assertTrue(device.isNaturalOrientation)

            // Verify text field is empty and hint is displayed
            verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

            // Enter string and click change button
            enterTextIntoTextField(R.id.editTextUserInput, s)
            clickChangeButton(R.id.changeTextBt)

            // Verify test string is displayed
            verifyTextStringIsDisplayed(R.id.textToBeChanged, s)

            // Clear text from text field
            clearTextField(R.id.editTextUserInput)

            // Set to landscape orientation
            device.setOrientationLeft()

            // Verify text field is empty and hint is displayed
            verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

            // Enter string and click change button
            enterTextIntoTextField(R.id.editTextUserInput, s)
            clickChangeButton(R.id.changeTextBt)

            // Ensure the test string id displayed
            verifyTextStringIsDisplayed(R.id.textToBeChanged, s)

            // Clear text from text field
            clearTextField(R.id.editTextUserInput)

            // Set the device back to Portrait
            device.setOrientationNatural()

            // Ensure the device is in Portrait
            Assert.assertTrue(device.isNaturalOrientation)
        }

    }

    @Test
    fun verifyShowTextActivityTest() {

        for (s in testData) {

            // Ensure the device is in Portrait
            Assert.assertTrue(device.isNaturalOrientation)

            // Verify text field is empty and hint is displayed
            verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

            // Enter string
            enterTextIntoTextField(R.id.editTextUserInput, s)

            // Click activity change button
            onView(withId(R.id.activityChangeTextBtn)).perform(click())

            // Now in the Show text activity
            verifyTextStringIsDisplayed(R.id.show_text_view, s)

            // Return to main activity to verify back stack functionality
            returnToMainActivity()

            // Clear text from text field
            clearTextField(R.id.editTextUserInput)

            // Verify text field is empty and hint is displayed
            verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

            // Enter string
            enterTextIntoTextField(R.id.editTextUserInput, s)

            // Verify change button id displayed after closing keyboard
            onView(withId(R.id.activityChangeTextBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            // Click change button
            onView(withId(R.id.activityChangeTextBtn)).perform(click())

            // Set to landscape orientation
            device.setOrientationLeft()

            // Now in the Show text activity
            verifyTextStringIsDisplayed(R.id.show_text_view, s)

            // Return to main activity to verify back stack functionality
            returnToMainActivityLandscape()

            // Clear text from text field
            clearTextField(R.id.editTextUserInput)

            // Set the device back to Portrait
            device.setOrientationNatural()

            // Ensure the device is in Portrait
            Assert.assertTrue(device.isNaturalOrientation)
        }

    }

    /**
     * Clicks the hardware up button to return to the main activity and verifies elements
     */
    private fun returnToMainActivity() {
        // Press back to return back to the main activity
        device.pressBack()
        verifyMainActivityUI()
    }

    /**
     * Clicks the hardware up button to return to the main activity from the landscape orientation
     */
    private fun returnToMainActivityLandscape() {
        device.pressBack()
        verifyMainActivityUIReturningFromShowTextLand()
    }

    /**
     * Verify text field is empty and hint text is displayed before entering text
     */
    private fun verifyTextIsEmpty(textFieldId: Int, hintText: Int) {
        onView(withId(textFieldId)).check(
            ViewAssertions.matches(
                ViewMatchers.withHint(hintText)
            )
        )
    }

    /**
     * Enter text into the text field
     */
    private fun enterTextIntoTextField(textFieldId: Int, testString: String) {
        onView(withId(textFieldId))
            .perform(typeText(testString), closeSoftKeyboard())
    }

    /**
     * Clicks the change text button
     */
    private fun clickChangeButton(changeTextButtonId: Int) {
        onView(withId(changeTextButtonId))
            .perform(click())
    }

    /**
     * The text that was entered into the text field is displayed in text view
     */
    private fun verifyTextStringIsDisplayed(headerTextViewId: Int, testString: String) {
        onView(withId(headerTextViewId)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    testString
                )
            )
        )
    }

    /**
     * Clears the text field
     */
    private fun clearTextField(textFieldId: Int) {
        onView(withId(textFieldId))
            .perform(clearText())
    }
}