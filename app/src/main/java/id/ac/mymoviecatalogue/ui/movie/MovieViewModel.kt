package id.ac.mymoviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ac.mymoviecatalogue.data.FilmRepository
import id.ac.mymoviecatalogue.data.source.local.entity.MoviesEntity
import id.ac.mymoviecatalogue.vo.Resource

class MovieViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = filmRepository.getListMovies()

    fun getFavoriteMovies() = filmRepository.getFavoriteMovies()
}