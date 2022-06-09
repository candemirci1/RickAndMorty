package com.example.rickandmorty.ui.home

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.databinding.ItemCharacterBinding


class HomeAdapter(
    private val characters: List<Character>,
    private val itemWidth: Int,
    private val onClick: (Int) -> Unit
): RecyclerView.Adapter<HomeAdapter.CharacterViewHolder>() {

    class CharacterViewHolder (val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentCharacter = characters[position]
        holder.binding.apply {
           rootLayout.updateLayoutParams {
               width = itemWidth
               height = itemWidth
           }
            tvCharacterName.text = currentCharacter.name
            Glide.with(root.context)
                .load(currentCharacter.image)
                .into(ivCharacter)

            root.setOnClickListener {
                onClick.invoke(currentCharacter.id)
            }

        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

}


