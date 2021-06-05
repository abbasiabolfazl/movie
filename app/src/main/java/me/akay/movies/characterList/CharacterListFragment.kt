package me.akay.movies.characterList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerFragment
import me.akay.movies.R
import me.akay.movies.datamodels.Character
import me.akay.movies.helper.EndlessRecyclerViewScrollListener
import me.akay.movies.helper.Status
import javax.inject.Inject

class CharacterListFragment : DaggerFragment(), CharacterAdapterDelegate {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CharacterListViewModel by viewModels { viewModelFactory }


    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingProgressBar: View
    private lateinit var loadMoreProgressBar: View

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_character_list, container, false)

        recyclerView = rootView.findViewById(R.id.rv_character)
        adapter = CharacterAdapter(this, requestManager)
        recyclerView.adapter = adapter

        loadingProgressBar = rootView.findViewById(R.id.pb_character)
        loadMoreProgressBar = rootView.findViewById(R.id.pb_character_loadMore)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characterLiveData.observe(viewLifecycleOwner, { result ->
            if (result.status == Status.SUCCESS) {
                adapter.setCharacter(result.data!!)
            }
        })

        viewModel.loadingLiveData.observe(viewLifecycleOwner, { visibility ->
            loadingProgressBar.visibility = visibility
        })

        viewModel.loadMoreLiveData.observe(viewLifecycleOwner, { visibility ->
            loadMoreProgressBar.visibility = visibility
        })

        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(recyclerView.layoutManager as GridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.onPageEnded()
            }
        })
    }

    override fun onClicked(character: Character) {
        Log.i("abbasi", "onClicked: ${character.name}")

        val bundle = bundleOf(
            "id" to character.id,
            "name" to character.name,
            "imageUrl" to character.thumbnail?.getBannerThumb()
        )

        findNavController().navigate(R.id.characterFragment, bundle)
    }
}