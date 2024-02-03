package com.example.hpcharactersapp.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.hpcharactersapp.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.main_activity)).check(matches(isDisplayed()))
    }
}