package com.example.hpcharactersapp.presentation.character_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.use_case.AddSuggestionUseCase
import com.example.hpcharactersapp.domain.use_case.GetCharactersUseCase
import com.example.hpcharactersapp.domain.use_case.GetSuggestionsUseCase
import com.example.hpcharactersapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSuggestionsUseCase: GetSuggestionsUseCase,
    private val addSuggestionUseCase: AddSuggestionUseCase
): ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>> = _suggestions

    init {
        Log.i("TAG","Character List View Model")

        getCharacters()
        getSuggestions()
    }

    fun onEvent(event: CharacterListEvent) {
        when(event) {
            is CharacterListEvent.OnQueryChange -> {
                _query.value = event.query
                if(event.query != "") {
                    addSuggestion()
                }
                getCharacters()
                getSuggestions()
            }
        }
    }

    fun getCharacters(
        query: String = _query.value.toString()
    ) {
        viewModelScope.launch {
            getCharactersUseCase.execute(query).collect { response ->
                when(response) {
                    is Resource.Error -> {
                        response.message?.let {  message ->
                            _errorMessage.value = message
                        }
                    }
                    is Resource.Loading -> {
                        _isLoading.value = response.isLoading
                    }
                    is Resource.Success -> {
                        response.data?.let { characterList ->
                            _characters.value = characterList
                        }
                    }
                }
            }
        }
    }

    fun getSuggestions() {
        viewModelScope.launch {
            getSuggestionsUseCase.execute().collect { response ->
                when(response) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let { suggestionList ->
                            _suggestions.value = suggestionList.distinct()
                        }
                    }
                }
            }
        }
    }

    fun addSuggestion(
        suggestion: String = _query.value.toString()
    ) {
        viewModelScope.launch {
            addSuggestionUseCase.execute(suggestion)
        }
    }
}