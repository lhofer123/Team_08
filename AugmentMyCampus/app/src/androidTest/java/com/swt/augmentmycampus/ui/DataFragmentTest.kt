package com.swt.augmentmycampus.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.swt.augmentmycampus.MainActivity
import com.swt.augmentmycampus.R
import com.swt.augmentmycampus.ui.data.DataFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DataFragmentTest {

    @get:Rule(order = 0)
    var hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var mainActivity: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigateToDataFragment() {
        onView(withId(R.id.navigation_data)).perform(click())
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))
    }

    @Test
    fun testDataFragmentHasHeaderLabel() {
        onView(withId(R.id.navigation_data)).perform(click())
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))
        onView(withId(R.id.label_header)).check(matches(isDisplayed()))
        onView(withId(R.id.label_header)).check(matches(withText("Agile Software Development")))
    }

    @Test
    fun testDataFragmentHasOtherLabels() {
        onView(withId(R.id.navigation_data)).perform(click())
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))

        onView(withId(R.id.label_number)).check(matches(isDisplayed()))
        onView(withId(R.id.label_number)).check(matches(withText("Number")))
        onView(withId(R.id.label_number_value)).check(matches(isDisplayed()))

        onView(withId(R.id.label_semester)).check(matches(isDisplayed()))
        onView(withId(R.id.label_semester)).check(matches(withText("Semester")))
        onView(withId(R.id.label_semester_value)).check(matches(isDisplayed()))

        onView(withId(R.id.label_ects)).check(matches(isDisplayed()))
        onView(withId(R.id.label_ects)).check(matches(withText("ECTS")))
        onView(withId(R.id.label_ects_value)).check(matches(isDisplayed()))

        onView(withId(R.id.label_lecturer)).check(matches(isDisplayed()))
        onView(withId(R.id.label_lecturer)).check(matches(withText("Lecturer")))
        onView(withId(R.id.label_lecturer_value)).check(matches(isDisplayed()))
    }

    @Test
    fun testDataFragmentHasExpandableViewForLecturerInformationContent() {
        onView(withId(R.id.navigation_data)).perform(click())
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))

        onView(withId(R.id.expandableListViewContent)).check(matches(isDisplayed()))
        onView(withId(R.id.listTitleContent)).check(matches(isDisplayed()))
        onView(withId(R.id.listTitleContent)).check(matches(withText("Content")))
    }

    @Test
    fun testDataFragmentHasExpandableViewForDates() {
        onView(withId(R.id.navigation_data)).perform(click())
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))

        onView(withId(R.id.expandableListViewDates)).check(matches(isDisplayed()))
        onView(withId(R.id.listTitleDates)).check(matches(isDisplayed()))
        onView(withId(R.id.listTitleDates)).check(matches(withText("Dates")))
    }

    @Test
    fun testDataFragmentHasRegisterButton() {
        onView(withId(R.id.navigation_data)).perform(click())
        //Here we would need to log in first to see this button later
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))

        onView(withId(R.id.registerButton)).perform(scrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun testDataFragmentRegisterButtonNameAndClick() {
        onView(withId(R.id.navigation_data)).perform(click())
        //Here we would need to log in first to see this button later
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))

        onView(withId(R.id.registerButton)).perform(scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.registerButton)).check(matches(withText("Register")))
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.registerButton)).check(matches(withText("Unregister")))
    }

    @Test
    fun testDataFragmentShowHideButtonClick() {
        onView(withId(R.id.navigation_data)).perform(click())
        onView(withId(R.id.fragment_data_id)).check(matches(isDisplayed()))

        onView(withId(R.id.show_hide_dates_button)).perform(scrollTo()).check(matches(isDisplayed()))
        onView(withId(R.id.show_hide_dates_button)).perform(click())
        onView(withText("Hide past events")).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.show_hide_dates_button)).perform(click())
        onView(withText("Show past events")).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.show_hide_dates_button)).perform(click())
        onView(withText("Hide past events")).check(matches(isDisplayed()))
    }
}