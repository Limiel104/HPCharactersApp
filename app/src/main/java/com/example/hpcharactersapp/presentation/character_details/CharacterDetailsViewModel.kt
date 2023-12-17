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

    private val _isBasicInfoSectionVisible = MutableLiveData(false)
    val isBasicInfoSectionExpanded: LiveData<Boolean> = _isBasicInfoSectionVisible

    private val _isMagicalCharacteristicsSectionVisible = MutableLiveData(false)
    val isMagicalCharacteristicsSectionExpanded: LiveData<Boolean> = _isMagicalCharacteristicsSectionVisible

    private val _isAffiliationSectionVisible = MutableLiveData(false)
    val isAffiliationSectionExpanded: LiveData<Boolean> = _isAffiliationSectionVisible

    init {
        Log.i("TAG","Character Details View Model")
    }

    fun onEvent(event: CharacterDetailsEvent) {
        when(event) {
            is CharacterDetailsEvent.OnPassedCharacterId -> {
                getCharacter(event.characterId)
            }
            is CharacterDetailsEvent.ToggleBasicInfoSection -> {
                _isBasicInfoSectionVisible.value = !_isBasicInfoSectionVisible.value!!
            }
            is CharacterDetailsEvent.ToggleMagicalCharacteristicSection -> {
                _isMagicalCharacteristicsSectionVisible.value = !_isMagicalCharacteristicsSectionVisible.value!!
            }
            is CharacterDetailsEvent.ToggleAffiliationSection -> {
                _isAffiliationSectionVisible.value = !_isAffiliationSectionVisible.value!!
            }
        }
    }

    fun getCharacter(characterId: String) {
        viewModelScope.launch {
            getCharacterUseCase.execute(characterId).collect { response ->
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