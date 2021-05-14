package id.ac.mymoviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.mymoviecatalogue.data.source.FilmRepository
import id.ac.mymoviecatalogue.data.source.remote.response.*

class DetailViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getMovieDetail(movieId: Int): LiveData<MovieDetailResponse> = filmRepository.getDetailMovie(movieId)

    fun getShowDetail(showId: Int): LiveData<TvShowDetailResponse> = filmRepository.getDetailTvShow(showId)
}