package com.example.hpcharactersapp.presentation.character_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.use_case.GetCharactersUseCase
import com.example.hpcharactersapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,

): ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> = _query

    init {
        Log.i("TAG","Character List View Model")

        getCharacters()
    }

    fun onEvent(event: CharacterListEvent) {
        when(event) {
            is CharacterListEvent.OnQueryChange -> {
                _query.value = event.query
                getCharacters()
            }
        }
    }

    fun getCharacters(
        query: String = _query.value.toString()
    ) {
        viewModelScope.launch {
            getCharactersUseCase.execute(query).collect { response ->
                when(response) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let { characterList ->
                            _characters.value = characterList
                        }
                    }
                }
            }
        }
    }
}