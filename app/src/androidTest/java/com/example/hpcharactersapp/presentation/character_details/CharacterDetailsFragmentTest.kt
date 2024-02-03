package com.example.hpcharactersapp.presentation.character_details

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.data.repository.CharacterRepository
import com.example.hpcharactersapp.data.repository.SuggestionRepository
import com.example.hpcharactersapp.domain.CharacterRepositoryTestImpl
import com.example.hpcharactersapp.domain.SuggestionRepositoryTestImpl
import com.example.hpcharactersapp.domain.use_case.AddSuggestionUseCase
import com.example.hpcharactersapp.domain.use_case.GetCharacterUseCase
import com.example.hpcharactersapp.domain.use_case.GetCharactersUseCase
import com.example.hpcharactersapp.domain.use_case.GetSuggestionsUseCase
import com.example.hpcharactersapp.launchFragmentInHiltContainer
import com.example.hpcharactersapp.presentation.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@UninstallModules(AppModule::class)
@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

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

    @Test
    fun test_isBasicInfoSectionVisibleAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {  }

        onView(withId(R.id.basicInfoFB)).check(matches(isDisplayed()))
        onView(withId(R.id.basicInfoFB)).perform(click())
        onView(withId(R.id.basicInfoRL)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isMagicalCharacteristicsSectionVisibleAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {  }

        onView(withId(R.id.magicalCharacteristicsFB)).check(matches(isDisplayed()))
        onView(withId(R.id.magicalCharacteristicsFB)).perform(click())
        onView(withId(R.id.magicalCharacteristicsRL)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isAffiliationSectionVisibleAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {  }

        onView(withId(R.id.affiliationFB)).check(matches(isDisplayed()))
        onView(withId(R.id.affiliationFB)).perform(click())
        onView(withId(R.id.affiliationRL)).check(matches(isDisplayed()))
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestAppModule {

        @Provides
        @Singleton
        fun provideCharacterRepository(): CharacterRepository {
            return CharacterRepositoryTestImpl()
        }

        @Provides
        @Singleton
        fun provideSuggestionRepository(): SuggestionRepository {
            return SuggestionRepositoryTestImpl()
        }

        @Provides
        @Singleton
        fun provideGetCharactersUseCase(repository: CharacterRepository): GetCharactersUseCase {
            return GetCharactersUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideGetCharacterUseCase(repository: CharacterRepository): GetCharacterUseCase {
            return GetCharacterUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideGetSuggestionsUseCase(repository: SuggestionRepository): GetSuggestionsUseCase {
            return GetSuggestionsUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideAddSuggestionUseCase(repository: SuggestionRepository): AddSuggestionUseCase {
            return AddSuggestionUseCase(repository)
        }
    }
}