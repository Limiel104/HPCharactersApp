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
        observeBasicInfoSection()
        observeMagicalCharacteristicsSection()
        observeAffiliationSection()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun displayCharacter() {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            Log.i("TAG",character.toString())

            with(binding) {
                characterName.text = character.name
                characterImage.load(character.imageUrl)

                if(character.alternateNames.isNotEmpty())
                    characterAlternateNames.text = character.alternateNames.joinToString(", ")
                else
                    characterAlternateNames.visibility = View.GONE

                characterSpecies.text = character.species
                characterGender.text = character.gender
                characterDateOfBirth.text = character.dateOfBirth
                characterAncestry.text = character.ancestry
                characterEyeColor.text = character.eyeColour
                characterHairColor.text = character.hairColour

                if(character.alive)
                    characterAliveStatus.setText(R.string.alive)
                else
                    characterAliveStatus.setText(R.string.dead)

                characterPatronus.text = character.patronus
                characterWandCore.text = character.wand.core
                characterWandLength.text = character.wand.length.toString()
                characterWandWood.text = character.wand.wood

                characterHouse.text = character.house

                if(character.hogwartsStaff) {
                    characterHStuff.setText(R.string.stuff)
                    characterHStudent.visibility = View.INVISIBLE
                }
                else if(character.hogwartsStudent) {
                    characterHStudent.setText(R.string.student)
                    characterHStuff.visibility = View.INVISIBLE
                }
                else {
                    characterHStudent.visibility = View.GONE
                    characterHStuff.visibility = View.GONE
                }

                basicInfoFB.setOnClickListener { viewModel.onEvent(CharacterDetailsEvent.ToggleBasicInfoSection) }
                magicalCharacteristicsFB.setOnClickListener { viewModel.onEvent(CharacterDetailsEvent.ToggleMagicalCharacteristicSection) }
                affiliationFB.setOnClickListener { viewModel.onEvent(CharacterDetailsEvent.ToggleAffiliationSection) }
            }
        }
    }

    fun observeBasicInfoSection() {
        viewModel.isBasicInfoSectionExpanded.observe(viewLifecycleOwner) { isSectionVisible ->
            if(isSectionVisible) {
                binding.basicInfoRL.visibility = View.VISIBLE
                binding.basicInfoExpandIcon.load(R.drawable.ic_expand_less)
            }
            else {
                binding.basicInfoRL.visibility = View.GONE
                binding.basicInfoExpandIcon.load(R.drawable.ic_expand_more)
            }
        }
    }

    fun observeMagicalCharacteristicsSection() {
        viewModel.isMagicalCharacteristicsSectionExpanded.observe(viewLifecycleOwner) { isSectionVisible ->
            if(isSectionVisible) {
                binding.magicalCharacteristicsRL.visibility = View.VISIBLE
                binding.magicalCharacteristicsExpandIcon.load(R.drawable.ic_expand_less)
            }
            else {
                binding.magicalCharacteristicsRL.visibility = View.GONE
                binding.magicalCharacteristicsExpandIcon.load(R.drawable.ic_expand_more)
            }
        }
    }

    fun observeAffiliationSection() {
        viewModel.isAffiliationSectionExpanded.observe(viewLifecycleOwner) { isSectionVisible ->
            if(isSectionVisible) {
                binding.affiliationRL.visibility = View.VISIBLE
                binding.affiliationExpandIcon.load(R.drawable.ic_expand_less)
            }
            else {
                binding.affiliationRL.visibility = View.GONE
                binding.affiliationExpandIcon.load(R.drawable.ic_expand_more)
            }
        }
    }
}