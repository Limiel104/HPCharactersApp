package com.example.hpcharactersapp.presentation.character_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("TAG","Character List Fragment")

        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.characterListRV.layoutManager = GridLayoutManager(requireContext(),2)
        binding.suggestionListRV.layoutManager = LinearLayoutManager(requireContext())

        displayCharacters()
        displaySuggestions()
        onCharacterSearch()
        onLoading()
        onError()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun displayCharacters() {
        viewModel.characters.observe(viewLifecycleOwner) {
            binding.characterListRV.adapter = viewModel.characters.value?.let { characters ->
                Log.i("TAG", characters.toString())
                CharacterRVAdapter(characters) { character ->
                    val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(character.id)
                    findNavController().navigate(action)
                }
            }
        }
    }

    fun displaySuggestions() {
        viewModel.suggestions.observe(viewLifecycleOwner) {
            binding.suggestionListRV.adapter = viewModel.suggestions.value?.let { suggestions ->
                SuggestionRVAdapter(suggestions) { suggestion ->
                    binding.searchView.setText(suggestion)
                }
            }
        }
    }

    fun onCharacterSearch() {
        binding.searchView.editText.setOnEditorActionListener { _, _, _ ->
            val query = binding.searchView.text
            binding.searchBar.setText(query)
            viewModel.onEvent(CharacterListEvent.OnQueryChange(query.toString()))
            binding.searchView.hide()
            return@setOnEditorActionListener false
        }
    }

    fun onLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            when(isLoading) {
                true -> binding.progressIndicator.visibility = View.VISIBLE
                false -> binding.progressIndicator.visibility = View.INVISIBLE
            }
        }
    }

    fun onError() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(context,message,Toast.LENGTH_LONG).show()
            }
        }
    }
}