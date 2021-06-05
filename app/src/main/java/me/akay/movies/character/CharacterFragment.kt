package me.akay.movies.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.android.support.DaggerFragment
import me.akay.movies.R
import me.akay.movies.helper.Status
import javax.inject.Inject

class CharacterFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CharacterViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var requestManager: RequestManager

    private var characterId: Int = 0
    private lateinit var name: String
    private lateinit var imageUrl: String

    private lateinit var avatarImageView: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterName: TextView
    private lateinit var comicStatus: TextView

    private lateinit var adapter: CharacterComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCharacterComics(arguments?.getInt("id")!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_character, container, false)

        avatarImageView = rootView.findViewById(R.id.iv_character_avatar)
        comicStatus = rootView.findViewById(R.id.tv_character_status)
        recyclerView = rootView.findViewById(R.id.rv_character_comics)

        adapter = CharacterComicsAdapter(requestManager)
        recyclerView.adapter = adapter

        characterName = rootView.findViewById(R.id.tv_character_name)
        characterName.text = arguments?.getString("name")

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData.observe(viewLifecycleOwner, { result ->
            if (result.status == Status.SUCCESS && result.data != null) {
                if (result.data.isEmpty()) {
                    comicStatus.text = "This character comics is empty!"
                } else {
                    comicStatus.visibility = View.GONE
                    adapter.setCharacter(result.data)
                }
            }
        })

        requestManager.load(arguments?.getString("imageUrl"))
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .into(avatarImageView)

    }
}