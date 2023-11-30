package com.example.hpcharactersapp.presentation.character_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hpcharactersapp.domain.model.Character
import com.example.hpcharactersapp.domain.use_case.GetCharacterUseCase
import com.example.hpcharactersapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    init {
        Log.i("TAG","Character Details View Model")

        getCharacter()
    }

    fun getCharacter() {
        viewModelScope.launch {
            getCharacterUseCase.execute("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8").collect { response ->
                when(response) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let { character ->
                            _character.value = character
                        }
                    }
                }
            }
        }
    }
}