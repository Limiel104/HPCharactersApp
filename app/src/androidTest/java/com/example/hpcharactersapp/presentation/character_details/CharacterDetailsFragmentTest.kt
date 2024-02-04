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
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.model.Wand
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

    private val wand =  Wand(
        wood = "holly",
        core = "phoenix tail feather",
        length = 11.0
    )

    private val character = Character(
        id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
        actor = "Daniel Radcliffe",
        alive = true,
        alternateNames = listOf("The Boy Who Lived","The Chosen One","Undesirable No. 1","Potty"),
        ancestry = "half-blood",
        dateOfBirth = "31-07-1980",
        eyeColour = "green",
        gender = "male",
        hairColour = "black",
        hogwartsStaff = false,
        hogwartsStudent= true,
        house = "Gryffindor",
        imageUrl = "https://ik.imagekit.io/hpapi/harry.jpg",
        name = "Harry Potter",
        patronus = "stag",
        species = "human",
        wand = wand,
        wizard = true,
        yearOfBirth = 1980
    )

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
        ) {}
    }

    @Test
    fun test_isCharacterDetailsFragmentLaunched() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {}

        onView(withId(R.id.character_details_fragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isCharacterListFragmentDisplayedCorrectlyOnLaunch() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {}

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
        ) {}

        val ids = listOf(
            R.id.basicInfoRL,
            R.id.speciesTV,
            R.id.characterSpecies,
            R.id.genderTV,
            R.id.characterGender,
            R.id.dateOfBirthTV,
            R.id.characterDateOfBirth,
            R.id.ancestryTV,
            R.id.characterAncestry,
            R.id.eyeColorTV,
            R.id.characterEyeColor,
            R.id.hairColorTV,
            R.id.characterHairColor,
            R.id.aliveStatusTV,
            R.id.characterAliveStatus
        )

        for(id in ids) {
            onView(withId(id)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        }

        onView(withId(R.id.basicInfoFB)).check(matches(isDisplayed()))
        onView(withId(R.id.basicInfoFB)).perform(click())

        for(id in ids) {
            onView(withId(id)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        }
    }

    @Test
    fun test_isMagicalCharacteristicsSectionVisibleAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {}

        val ids = listOf(
            R.id.magicalCharacteristicsRL,
            R.id.patronusTV,
            R.id.characterPatronus,
            R.id.wandCoreTV,
            R.id.characterWandCore,
            R.id.wandLengthTV,
            R.id.characterWandLength,
            R.id.wandWoodTV,
            R.id.characterWandWood
        )

        for(id in ids) {
            onView(withId(id)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        }

        onView(withId(R.id.magicalCharacteristicsFB)).check(matches(isDisplayed()))
        onView(withId(R.id.magicalCharacteristicsFB)).perform(click())

        for(id in ids) {
            onView(withId(id)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        }
    }

    @Test
    fun test_isAffiliationSectionVisibleAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {}

        val ids = listOf(
            R.id.affiliationRL,
            R.id.houseTV,
            R.id.characterHouse,
            R.id.hogwartsTV,
            R.id.characterHStudent
        )

        for(id in ids) {
            onView(withId(id)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
        onView(withId(R.id.characterHStuff)).check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.affiliationFB)).check(matches(isDisplayed()))
        onView(withId(R.id.affiliationFB)).perform(click())

        for(id in ids) {
            onView(withId(id)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        }
        onView(withId(R.id.characterHStuff)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

    @Test
    fun test_isBasicInformationSectionDisplayedCorrectlyAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {}

        onView(withId(R.id.basicInfoFB)).check(matches(isDisplayed()))
        onView(withId(R.id.basicInfoFB)).perform(click())

        onView(withId(R.id.speciesTV)).check(matches(withText(R.string.species)))
        onView(withId(R.id.characterSpecies)).check(matches(withText(character.species)))
        onView(withId(R.id.genderTV)).check(matches(withText(R.string.gender)))
        onView(withId(R.id.characterGender)).check(matches(withText(character.gender)))
        onView(withId(R.id.dateOfBirthTV)).check(matches(withText(R.string.date_of_birth)))
        onView(withId(R.id.characterDateOfBirth)).check(matches(withText(character.dateOfBirth)))
        onView(withId(R.id.ancestryTV)).check(matches(withText(R.string.ancestry)))
        onView(withId(R.id.characterAncestry)).check(matches(withText(character.ancestry)))
        onView(withId(R.id.eyeColorTV)).check(matches(withText(R.string.eye_color)))
        onView(withId(R.id.characterEyeColor)).check(matches(withText(character.eyeColour)))
        onView(withId(R.id.hairColorTV)).check(matches(withText(R.string.hair_color)))
        onView(withId(R.id.characterHairColor)).check(matches(withText(character.hairColour)))
        onView(withId(R.id.aliveStatusTV)).check(matches(withText(R.string.alive_status)))
        onView(withId(R.id.characterAliveStatus)).check(matches(withText(R.string.alive)))
    }

    @Test
    fun test_isMagicalCharacteristicsSectionDisplayedCorrectlyAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {}

        onView(withId(R.id.magicalCharacteristicsFB)).check(matches(isDisplayed()))
        onView(withId(R.id.magicalCharacteristicsFB)).perform(click())

        onView(withId(R.id.patronusTV)).check(matches(withText(R.string.patronus)))
        onView(withId(R.id.characterPatronus)).check(matches(withText(character.patronus)))
        onView(withId(R.id.wandCoreTV)).check(matches(withText(R.string.wand_core)))
        onView(withId(R.id.characterWandCore)).check(matches(withText(character.wand.core)))
        onView(withId(R.id.wandLengthTV)).check(matches(withText(R.string.wand_length)))
        onView(withId(R.id.characterWandLength)).check(matches(withText(character.wand.length.toString())))
        onView(withId(R.id.wandWoodTV)).check(matches(withText(R.string.wand_wood)))
        onView(withId(R.id.characterWandWood)).check(matches(withText(character.wand.wood)))
    }

    @Test
    fun test_isAffiliationSectionDisplayedCorrectlyAfterClick() {
        val bundle = Bundle()
        bundle.putString("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8","characterId")

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = bundle
        ) {}

        onView(withId(R.id.affiliationFB)).check(matches(isDisplayed()))
        onView(withId(R.id.affiliationFB)).perform(click())

        onView(withId(R.id.houseTV)).check(matches(withText(R.string.house)))
        onView(withId(R.id.characterHouse)).check(matches(withText(character.house)))
        onView(withId(R.id.hogwartsTV)).check(matches(withText(R.string.hogwarts)))
        onView(withId(R.id.characterHStudent)).check(matches(withText(R.string.student)))
        onView(withId(R.id.characterHStuff)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
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