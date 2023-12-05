package com.example.hpcharactersapp.presentation.character_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hpcharactersapp.databinding.FragmentCharacterDetailsBinding

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("TAG","Character Details Fragment")

        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayCharacter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun displayCharacter() {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            Log.i("TAG",character.toString())
        }
    }
}