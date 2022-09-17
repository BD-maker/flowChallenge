package com.example.flowchallengue.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowchallengue.R
import com.example.flowchallengue.core.domain.model.CharacterModel
import com.example.flowchallengue.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(
    private val characters: List<CharacterModel>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CharacterAdapter.CharacterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterHolder(
            layoutInflater.inflate(
                R.layout.character_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.render(characters[position])
    }

    override fun getItemCount(): Int {
        return if (characters.isNotEmpty()) {
            characters.size
        } else {
            0
        }
    }


    inner class CharacterHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        fun render(character: CharacterModel) {
            val binding = CharacterItemBinding.bind(view)
            binding.tvItemName.text = character.name
            Picasso.get().load(character.image).into(binding.ivItemImage)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClickListener.onItemClick(characters[position].id.toString() ?: "")
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String)
    }
}

