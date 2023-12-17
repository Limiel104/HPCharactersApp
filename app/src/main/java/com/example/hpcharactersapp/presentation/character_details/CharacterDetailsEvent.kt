package com.example.hpcharactersapp.presentation.character_details

sealed class CharacterDetailsEvent {
    data class OnPassedCharacterId(val characterId: String): CharacterDetailsEvent()
    data object ToggleBasicInfoSection: CharacterDetailsEvent()
    data object ToggleMagicalCharacteristicSection: CharacterDetailsEvent()
    data object ToggleAffiliationSection: CharacterDetailsEvent()
}