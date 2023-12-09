package com.example.hpcharactersapp.presentation.character_list

sealed class CharacterListEvent {
    data class OnQueryChange(val query: String): CharacterListEvent()
    data object OnShowSuggestions: CharacterListEvent()
}