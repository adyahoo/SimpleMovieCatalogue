package id.ac.mymoviecatalogue.ui.favorite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.mymoviecatalogue.databinding.FragmentShowBinding
import id.ac.mymoviecatalogue.ui.show.ShowAdapter
import id.ac.mymoviecatalogue.ui.show.ShowViewModel
import id.ac.mymoviecatalogue.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentShowBinding
    private lateinit var viewModel: ShowViewModel
    private lateinit var showAdapter: ShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[ShowViewModel::class.java]
            showAdapter = ShowAdapter()

            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { favShows ->
                if (favShows.isNotEmpty()) {
                    showAdapter.submitList(favShows)
                } else {
                    binding.tvShowError.visibility = View.VISIBLE
                }
            })

            with(binding.rvShow) {
                layoutManager = LinearLayoutManager(context)
                adapter = showAdapter
            }
        }
    }
}