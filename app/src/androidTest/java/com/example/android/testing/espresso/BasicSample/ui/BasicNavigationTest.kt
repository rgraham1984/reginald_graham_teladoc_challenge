package com.example.android.testing.espresso.BasicSample.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
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

        const val TEST_STRING = "This is a test"
    }

    @Test
    fun verifyMainActivityTest() {

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

        // Verify text field is empty and hint is displayed
        verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

        // Enter string and click change button
        enterTextIntoTextField(R.id.editTextUserInput)
        clickChangeButton(R.id.changeTextBt)

        // Verify test string is displayed
        verifyTextStringIsDisplayed(R.id.textToBeChanged)

        // Clear text from text field
        clearTextField(R.id.editTextUserInput)

        // Set to landscape orientation
        device.setOrientationLeft()

        // Verify text field is empty and hint is displayed
        verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

        // Enter string and click change button
        enterTextIntoTextField(R.id.editTextUserInput)
        clickChangeButton(R.id.changeTextBt)

        // Ensure the test string id displayed
        verifyTextStringIsDisplayed(R.id.textToBeChanged)

        // Clear text from text field
        clearTextField(R.id.editTextUserInput)

        // Set the device back to Portrait
        device.setOrientationNatural()

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

    }

    @Test
    fun verifyShowTextActivityTest() {

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

        // Verify text field is empty and hint is displayed
        verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

        // Enter string
        enterTextIntoTextField(R.id.editTextUserInput)

        // Click activity change button
        onView(withId(R.id.activityChangeTextBtn)).perform(click())

        // Now in the Show text activity
        verifyShowActivityScreenDisplaysTestString(R.id.show_text_view)

        // Return to main activity to verify back stack functionality
        returnToMainActivity()

        // Clear text from text field
        clearTextField(R.id.editTextUserInput)

        // Verify text field is empty and hint is displayed
        verifyTextIsEmpty(R.id.editTextUserInput, R.string.type_something)

        // Enter string
        enterTextIntoTextField(R.id.editTextUserInput)

        // Verify change button id displayed after closing keyboard
        onView(withId(R.id.activityChangeTextBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click change button
        onView(withId(R.id.activityChangeTextBtn)).perform(click())

        // Set to landscape orientation
        device.setOrientationLeft()

        // Now in the Show text activity
        verifyShowActivityScreenDisplaysTestString(R.id.show_text_view)

        // Return to main activity to verify back stack functionality
        returnToMainActivityLandscape()

        // Clear text from text field
        clearTextField(R.id.editTextUserInput)

        // Set the device back to Portrait
        device.setOrientationNatural()

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

    }

    private fun returnToMainActivity() {
        // Press back to return back to the main activity
        device.pressBack()
        verifyMainActivityUI()
    }

    private fun returnToMainActivityLandscape() {
        device.pressBack()
        verifyMainActivityUIReturningFromShowTextLand()
    }

    private fun verifyTextIsEmpty(id: Int, hint: Int) {
        onView(withId(id)).check(
            ViewAssertions.matches(
                ViewMatchers.withHint(hint)
            )
        )
    }

    private fun enterTextIntoTextField(id: Int) {
        onView(withId(id))
            .perform(typeText(TEST_STRING), ViewActions.closeSoftKeyboard())
    }

    private fun clickChangeButton(id: Int) {
        onView(withId(id))
            .perform(click())
    }

    private fun verifyTextStringIsDisplayed(id: Int) {
        onView(withId(id)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    TEST_STRING
                )
            )
        )
    }

    private fun verifyShowActivityScreenDisplaysTestString(id: Int) {
        onView(withId(id)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    TEST_STRING
                )
            )
        )
    }

    private fun clearTextField(id: Int) {
        onView(withId(id))
            .perform(clearText())
    }
}