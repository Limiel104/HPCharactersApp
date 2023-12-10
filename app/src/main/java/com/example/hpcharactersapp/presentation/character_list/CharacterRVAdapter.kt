package com.example.hpcharactersapp.presentation.character_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.databinding.CharacterListItemBinding
import com.example.hpcharactersapp.domain.model.Character

class CharacterRVAdapter(
    private val characters: List<Character>,
    private val onItemClicked: (Character) -> Unit
): RecyclerView.Adapter<CharacterRVAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        return CharacterViewHolder(
            binding = CharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked = { onItemClicked(characters[it]) }
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) {
        holder.bind(characters[position])
    }

    inner class CharacterViewHolder(
        private val binding: CharacterListItemBinding,
        private val onItemClicked: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.characterListItem.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }

        fun bind(character: Character) {
            binding.itemName.text = character.name
            if(character.imageUrl == "") {
                binding.itemImage.load(R.drawable.ic_launcher_background)
            }
            else {
                binding.itemImage.load(character.imageUrl)
            }
        }
    }
}