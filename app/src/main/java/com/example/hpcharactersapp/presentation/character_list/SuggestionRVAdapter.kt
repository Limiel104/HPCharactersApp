package com.example.hpcharactersapp.presentation.character_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hpcharactersapp.R
import com.example.hpcharactersapp.databinding.SuggestionListItemBinding

class SuggestionRVAdapter(
    private val suggestions: List<String>,
    private val onItemClicked: (String) -> Unit
): RecyclerView.Adapter<SuggestionRVAdapter.SuggestionViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuggestionViewHolder {
        return SuggestionViewHolder(
            binding = SuggestionListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClicked = { onItemClicked(suggestions[it]) }
        )
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }

    override fun onBindViewHolder(
        holder: SuggestionViewHolder,
        position: Int
    ) {
        holder.bind(suggestions[position])
    }

    inner class SuggestionViewHolder(
        private val binding: SuggestionListItemBinding,
        private val onItemClicked: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.suggestionListItem.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }

        fun bind(suggestion: String) {
            binding.suggestionText.text = suggestion
            binding.icon.load(R.drawable.ic_history)
        }
    }
}