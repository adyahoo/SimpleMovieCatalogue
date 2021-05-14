package id.ac.mymoviecatalogue.ui.movie

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.mymoviecatalogue.R
import id.ac.mymoviecatalogue.data.MoviesEntity
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemMovie
import id.ac.mymoviecatalogue.databinding.FragmentMovieBinding
import id.ac.mymoviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progbarMovie.visibility = View.VISIBLE

        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                binding.progbarMovie.visibility = View.GONE
                if (movies.isEmpty()) {
                    binding.tvMovieError.visibility = View.VISIBLE
                }

                movieAdapter.setMovie(parseToEntity(movies))

                with(binding.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home_section -> {
                activity?.title = getString(R.string.home_section)
            }
            R.id.action_favorite_section -> {
                activity?.title = getString(R.string.favorite_section)
            }
        }
        item.setChecked(true)
        return super.onOptionsItemSelected(item)
    }

    private fun parseToEntity(movies: List<ResultsItemMovie>): ArrayList<MoviesEntity> {
        val movieList = ArrayList<MoviesEntity>()
        for (movie in movies) {
            val moviesEntity = MoviesEntity(
                movie.id,
                movie.title,
                movie.releaseDate,
                movie.overview,
                null,
                null,
                movie.posterPath
            )
            movieList.add(moviesEntity)
        }

        return movieList
    }
}