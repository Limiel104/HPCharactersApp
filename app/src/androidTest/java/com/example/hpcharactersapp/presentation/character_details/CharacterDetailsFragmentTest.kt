package com.example.hpcharactersapp.presentation.character_details

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailsFragmentTest {

    @Test
    fun test_isCharacterDetailsFragmentLaunchedInHiltContainer() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {  }
    }

    @Test
    fun test_isCharacterDetailsFragmentLaunched() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {  }

        onView(withId(R.id.character_details_fragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isCharacterListFragmentDisplayedCorrectlyOnLaunch() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {  }

        onView(withId(R.id.characterName)).check(matches(isDisplayed()))
        onView(withId(R.id.characterImage)).check(matches(isDisplayed()))
        onView(withId(R.id.characterAlternateNames)).check(matches(isDisplayed()))

        onView(withId(R.id.basicInfoFB)).check(matches(isDisplayed()))
        onView(withId(R.id.basicInfoTV)).check(matches(withText(R.string.basic_info)))
        onView(withId(R.id.basicInfoExpandIcon)).check(matches(isDisplayed()))

        onView(withId(R.id.basicInfoRL)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.magicalCharacteristicsFB)).check(matches(isDisplayed()))
        onView(withId(R.id.magicalCharacteristicsTV)).check(matches(withText(R.string.magical_characteristics)))
        onView(withId(R.id.magicalCharacteristicsExpandIcon)).check(matches(isDisplayed()))

        onView(withId(R.id.magicalCharacteristicsRL)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.affiliationFB)).check(matches(isDisplayed()))
        onView(withId(R.id.affiliationTV)).check(matches(withText(R.string.affiliation)))
        onView(withId(R.id.affiliationExpandIcon)).check(matches(isDisplayed()))

        onView(withId(R.id.affiliationRL)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}