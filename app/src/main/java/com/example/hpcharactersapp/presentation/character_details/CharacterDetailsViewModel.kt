package com.example.hpcharactersapp.presentation.character_details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hpcharactersapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {

    init {
        Log.i("TAG","Character Details View Model")
    }
}