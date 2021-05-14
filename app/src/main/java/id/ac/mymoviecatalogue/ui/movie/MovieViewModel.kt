package id.ac.mymoviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.mymoviecatalogue.data.source.FilmRepository
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemMovie

class MovieViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getMovies(): LiveData<List<ResultsItemMovie>> = filmRepository.getListMovies()
}