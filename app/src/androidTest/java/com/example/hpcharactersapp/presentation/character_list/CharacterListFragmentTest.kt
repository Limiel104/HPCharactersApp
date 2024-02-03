package com.example.hpcharactersapp.presentation.character_list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.R.id.open_search_view_edit_text
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_isCharacterListFragmentLaunchedInHiltContainer() {
        launchFragmentInHiltContainer<CharacterListFragment> {  }
    }

    @Test
    fun test_isCharacterListFragmentLaunched() {
        launchFragmentInHiltContainer<CharacterListFragment> {  }

        onView(withId(R.id.character_list_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isCharacterListFragmentDisplayedCorrectly() {
        launchFragmentInHiltContainer<CharacterListFragment> {  }

        onView(withId(R.id.nestedScrollView)).check(matches(isDisplayed()))
        onView(withId(R.id.characterListRV)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.appBarLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.searchBar)).check(matches(isDisplayed()))
        onView(withHint(R.string.search_dots)).check(matches(isDisplayed()))
        onView(withId(R.id.progressIndicator)).check(matches( isDisplayed()))
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(withHint(R.string.search)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun test_searchViewHintIsDisplayedAfterClick() {
        launchFragmentInHiltContainer<CharacterListFragment> {  }

        onView(withHint(R.string.search)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.searchBar)).perform(click())
        onView(withHint(R.string.search)).check(matches(isDisplayed()))
    }

    @Test
    fun test_searchViewContainsInsertedText() {
        launchFragmentInHiltContainer<CharacterListFragment> {  }

        onView(withHint(R.string.search)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.searchBar)).perform(click())
        onView(withId(open_search_view_edit_text)).check(matches(withText("")))
        onView(withId(open_search_view_edit_text)).perform(typeText("new text"))
        onView(withText("new text")).check(matches(isDisplayed()))
        onView(withId(open_search_view_edit_text)).check(matches(withText("new text")))
    }
}