package com.example.hpcharactersapp.presentation.character_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.databinding.FragmentCharacterDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private val passedArg: CharacterDetailsFragmentArgs by navArgs()

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

        viewModel.onEvent(CharacterDetailsEvent.OnPassedCharacterId(passedArg.characterId))
        displayCharacter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun displayCharacter() {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            Log.i("TAG",character.toString())

            binding.characterName.text = character.name
            binding.characterImage.load(character.imageUrl)

            if(character.alternateNames.isNotEmpty()) {
                binding.characterAlternateNames.text = character.alternateNames.joinToString(", ")
            }
            else {
                binding.characterAlternateNames.visibility = View.GONE
            }

            binding.basicInfoExpandIcon.load(R.drawable.ic_expand_more)
            binding.characterSpecies.text = character.species
            binding.characterGender.text = character.gender
            binding.characterDateOfBirth.text = character.dateOfBirth
            binding.characterAncestry.text = character.ancestry
            binding.characterEyeColor.text = character.eyeColour
            binding.characterHairColor.text = character.hairColour

            if(character.alive) {
                binding.characterAliveStatus.setText(R.string.alive)
            }
            else {
                binding.characterAliveStatus.setText(R.string.dead)
            }

            binding.magicalCharacteristicsExpandIcon.load(R.drawable.ic_expand_more)
            binding.characterPatronus.text = character.patronus
            binding.characterWandCore.text = character.wand.core
            binding.characterWandLength.text = character.wand.length.toString()
            binding.characterWandWood.text = character.wand.wood

            binding.affiliationExpandIcon.load(R.drawable.ic_expand_more)
            binding.characterHouse.text = character.house

            if(character.hogwartsStaff) {
                binding.characterHStuff.setText(R.string.stuff)
                binding.characterHStudent.visibility = View.INVISIBLE
            }
            else if(character.hogwartsStudent) {
                binding.characterHStudent.setText(R.string.student)
                binding.characterHStuff.visibility = View.INVISIBLE
            }
            else {
                binding.characterHStudent.visibility = View.GONE
                binding.characterHStuff.visibility = View.GONE
            }
        }
    }
}