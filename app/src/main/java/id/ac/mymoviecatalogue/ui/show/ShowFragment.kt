package id.ac.mymoviecatalogue.ui.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.mymoviecatalogue.data.TvShowEntity
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemTvShow
import id.ac.mymoviecatalogue.databinding.FragmentShowBinding
import id.ac.mymoviecatalogue.viewmodel.ViewModelFactory

class ShowFragment : Fragment() {
    private lateinit var binding: FragmentShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progbarShow.visibility = View.VISIBLE
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ShowViewModel::class.java]

        viewModel.getShows().observe(viewLifecycleOwner, { shows ->
            binding.progbarShow.visibility = View.GONE
            if (shows.isEmpty()) {
                binding.tvShowError.visibility = View.VISIBLE
            }

            val showAdapter = ShowAdapter()
            showAdapter.setShow(parseToEntity(shows))

            with(binding.rvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = showAdapter
            }
        })
    }

    private fun parseToEntity(shows: List<ResultsItemTvShow>): ArrayList<TvShowEntity> {
        val showList = ArrayList<TvShowEntity>()
        for (show in shows) {
            val showEntity = TvShowEntity(
                show.id,
                show.name,
                show.firstAirDate,
                show.overview,
                null,
                null,
                show.posterPath
            )
            showList.add(showEntity)
        }
        return showList
    }
}