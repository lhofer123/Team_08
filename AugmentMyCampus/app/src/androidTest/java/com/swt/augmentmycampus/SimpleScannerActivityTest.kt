package com.swt.augmentmycampus

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleScannerActivityTest {

   // @Rule
   // var intentsRule: IntentsTestRule<CameraActivity> = IntentsTestRule(CameraActivity::class.java)

    @get:Rule
    var mainActivity: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testMainActivity() {
        mainActivity
        onView(withId(R.id.scanButton)).check(matches(isDisplayed()))
        onView(withId(R.id.scanButton)).check(matches(withText("Scan")))
        onView(withId(R.id.qrCodeText)).check(matches(isDisplayed()))
        onView(withId(R.id.qrCodeText)).check(matches(withText("Press 'Scan' below")))
    }

    @Test
    fun testScannerView() {
        mainActivity
        onView(withId(R.id.scanButton)).perform(click())
        onView(withClassName(Matchers.containsString("ZXingScannerView"))).check(matches(isDisplayed()))
    }

    @Test
    fun testScannerOnQrCode() {
        mainActivity
        onView(withId(R.id.qrCodeText)).check(matches(isDisplayed()))
        onView(withText("Press 'Scan' below")).check(matches(isDisplayed()))
        onView(withId(R.id.scanButton)).perform(click())
        onView(withClassName(Matchers.containsString("ZXingScannerView")))

        // Wait for user to scan qr-code
        Thread.sleep(5500);

        onView(withClassName(Matchers.containsString("ZXingScannerView"))).check(doesNotExist())
        onView(withText("Press 'Scan' below")).check(doesNotExist())
    }
}