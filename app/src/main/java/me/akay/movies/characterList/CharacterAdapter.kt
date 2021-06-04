package me.akay.movies.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import me.akay.movies.R
import me.akay.movies.datamodels.Character
import javax.inject.Inject

class CharacterAdapter @Inject constructor(val characterAdapterDelegate: CharacterAdapterDelegate, val requestManager: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var characters: ArrayList<Character> = ArrayList()

    fun setCharacter(list: List<Character>) {
        if (list.isEmpty()) return

        if (characters.isNotEmpty()) {
            val sizeTemp = characters.size
            characters.addAll(sizeTemp, list)
            notifyItemRangeInserted(sizeTemp, characters.size)
        } else {
            characters.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun firstLoading(): Boolean = characters.isEmpty()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterViewHolder).bind(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = itemView.findViewById(R.id.item_character_name)
        private val avatarImageView: ImageView = itemView.findViewById(R.id.item_character_imageview)

        fun bind(character: Character) {
            itemView.setOnClickListener { characterAdapterDelegate.onClicked(character) }

            nameTextView.text = character.name

            requestManager.load(character.thumbnail?.getSmallThumb())
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(avatarImageView)
        }
    }
}