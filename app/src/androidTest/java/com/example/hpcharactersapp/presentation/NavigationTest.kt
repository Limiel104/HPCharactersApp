package com.example.hpcharactersapp.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.data.repository.CharacterRepository
import com.example.hpcharactersapp.data.repository.SuggestionRepository
import com.example.hpcharactersapp.domain.CharacterRepositoryTestImpl
import com.example.hpcharactersapp.domain.SuggestionRepositoryTestImpl
import com.example.hpcharactersapp.domain.use_case.AddSuggestionUseCase
import com.example.hpcharactersapp.domain.use_case.GetCharacterUseCase
import com.example.hpcharactersapp.domain.use_case.GetCharactersUseCase
import com.example.hpcharactersapp.domain.use_case.GetSuggestionsUseCase
import com.example.hpcharactersapp.presentation.character_list.CharacterRVAdapter
import com.example.hpcharactersapp.presentation.di.AppModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Singleton

@UninstallModules(AppModule::class)
@HiltAndroidTest
class NavigationTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_selectListItem_navigateToCharacterDetailsFragment() {
        onView(withId(R.id.character_list_fragment)).check(matches(isDisplayed()))

        onView(withId(R.id.characterListRV)).perform(
            actionOnItemAtPosition<CharacterRVAdapter.CharacterViewHolder>(
                1,click()
            )
        )

        onView(withId(R.id.character_details_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navigateBactToCharacterListFragment() {
        onView(withId(R.id.character_list_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.characterListRV)).perform(
            actionOnItemAtPosition<CharacterRVAdapter.CharacterViewHolder>(
                1,click()
            )
        )
        onView(withId(R.id.character_details_fragment)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.character_list_fragment)).check(matches(isDisplayed()))
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