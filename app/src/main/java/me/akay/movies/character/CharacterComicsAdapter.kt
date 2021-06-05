package me.akay.movies.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import me.akay.movies.R
import me.akay.movies.datamodels.Comic
import javax.inject.Inject

class CharacterComicsAdapter @Inject constructor(val requestManager: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var comics: ArrayList<Comic> = ArrayList()

    fun setCharacter(list: List<Comic>) {
        if (list.isEmpty()) return

        comics.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterComicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character_comic, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterComicViewHolder).bind(comics[position])
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    inner class CharacterComicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = itemView.findViewById(R.id.item_character_name)
        private val avatarImageView: ImageView = itemView.findViewById(R.id.item_character_imageview)

        fun bind(comic: Comic) {
            nameTextView.text = comic.title

            requestManager.load(comic.thumbnail?.getBannerThumb())
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(avatarImageView)
        }
    }
}