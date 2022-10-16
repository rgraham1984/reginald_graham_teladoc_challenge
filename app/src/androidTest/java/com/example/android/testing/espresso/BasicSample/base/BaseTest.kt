package com.example.android.testing.espresso.BasicSample.base

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.example.android.testing.espresso.BasicSample.MainActivity
import com.example.android.testing.espresso.BasicSample.R
import org.junit.After
import org.junit.Before
import org.junit.Rule

/**
 * This class servers as a base class for end to end test cases and provides
 * common functionality
 */

open class BaseTest {

    /**
     * The main activity will be launched with each test
     */
    @get:Rule
    open var activityScenarioRule = activityScenarioRule<MainActivity>()

    lateinit var device: UiDevice

    @Before
    @Throws(Exception::class)
    open fun setUp() {

        // Verify layout correct prior to verifying functionality
        verifyMainActivityUI()

        // Reference to the device so that we can change orientation and utilize hardware up
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    }

    @After
    open fun tearDown() {

        // Add any logic that would be beneficial during the teardown of the test

    }

    /**
     * This code verifies that all of the UI displayed expected on the main activity is displayed
     */
    fun verifyMainActivityUI() {

        onView(withId(R.id.textToBeChanged)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.changeTextBt)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.editTextUserInput)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.activityChangeTextBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    /**
     * The sample code does not display the open activity change text when in landscape on my
     * available devices.This looks to be a bug in their code or unhandled user case. I created
     * this function to verify the UI that is displayed in landscape.
     *
     */
    fun verifyMainActivityUIReturningFromShowTextLand() {

        onView(withId(R.id.textToBeChanged)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.changeTextBt)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.editTextUserInput)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}