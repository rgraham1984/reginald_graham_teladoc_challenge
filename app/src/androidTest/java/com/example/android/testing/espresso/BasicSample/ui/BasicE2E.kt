package com.example.android.testing.espresso.BasicSample.ui

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import com.example.android.testing.espresso.BasicSample.R
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.testing.espresso.BasicSample.base.BaseTest
import org.junit.Assert
import org.junit.Test

class BasicE2E : BaseTest() {

    companion object {

        const val TEST_STRING = "This is a test"
    }

    @Test
    fun verifyMainActivityTest(){

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

        // Verify text field is empty and hint is displayed
        onView(withId(R.id.editTextUserInput)).check(
            ViewAssertions.matches(
                ViewMatchers.withHint(R.string.type_something)
            )
        )

        // Enter string and click change button
        onView(withId(R.id.editTextUserInput)).perform(typeText(TEST_STRING), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())

        // Ensure the test string id displayed
        onView(withId(R.id.textToBeChanged)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    TEST_STRING
                )
            )
        )

        // Clear text from text field
        onView(withId(R.id.editTextUserInput)).perform(clearText())

        // Set to landscape orientation
        device.setOrientationLeft()

        // Verify text field is empty and hint is displayed
        onView(withId(R.id.editTextUserInput)).check(
            ViewAssertions.matches(
                ViewMatchers.withHint(R.string.type_something)
            )
        )

        // Enter string and click change button
        onView(withId(R.id.editTextUserInput)).perform(typeText(TEST_STRING), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(ViewActions.click())

        // Ensure the test string id displayed
        onView(withId(R.id.textToBeChanged)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    TEST_STRING
                )
            )
        )

        // Clear text from text field
        onView(withId(R.id.editTextUserInput)).perform(clearText())

        // Set the device back to Portrait
        device.setOrientationNatural()

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

    }

    @Test
    fun verifyShowTextActivityTest(){

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

        // Verify text field is empty and hint is displayed
        onView(withId(R.id.editTextUserInput)).check(
            ViewAssertions.matches(
                ViewMatchers.withHint(R.string.type_something)
            )
        )

        // Verify text field is empty and hint is displayed
        onView(withId(R.id.editTextUserInput)).perform(typeText(TEST_STRING), ViewActions.closeSoftKeyboard())

        // Click change button
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())

        // Now in the Show text activity
        onView(withId(R.id.show_text_view)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    TEST_STRING
                )
            )
        )

        // Return to main activity to verify back stack functionality
        returnToMainActivity()

        // Clear text from text field
        onView(withId(R.id.editTextUserInput)).perform(clearText())

        // Verify text field is empty and hint is displayed
        onView(withId(R.id.editTextUserInput)).check(
            ViewAssertions.matches(
                ViewMatchers.withHint(R.string.type_something)
            )
        )

        // Verify text field is empty and hint is displayed
        onView(withId(R.id.editTextUserInput)).perform(typeText(TEST_STRING), ViewActions.closeSoftKeyboard())

        // Verify change button id displayed after closing keyboard
        onView(withId(R.id.activityChangeTextBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click change button
        onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())

        // Set to landscape orientation
        device.setOrientationLeft()

        // Now in the Show text activity
        onView(withId(R.id.show_text_view)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    TEST_STRING
                )
            )
        )

        // Return to main activity to verify back stack functionality
        returnToMainActivityLandscape()

        // Clear text from text field
        onView(withId(R.id.editTextUserInput)).perform(clearText())

        // Set the device back to Portrait
        device.setOrientationNatural()

        // Ensure the device is in Portrait
        Assert.assertTrue(device.isNaturalOrientation)

    }
    private fun returnToMainActivity(){
        // Press back to return back to the main activity
        device.pressBack()
        verifyMainActivityUI()

    }

    private fun returnToMainActivityLandscape(){
        device.pressBack()
        verifyMainActivityUIReturningFromShowTextLand()
    }

}