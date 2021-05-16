package id.ac.mymoviecatalogue.ui.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.ac.mymoviecatalogue.data.FilmRepository
import id.ac.mymoviecatalogue.data.source.local.entity.TvShowEntity
import id.ac.mymoviecatalogue.vo.Resource

class ShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getShows(): LiveData<Resource<PagedList<TvShowEntity>>> = filmRepository.getListTvShow()

    fun getFavoriteTvShows() = filmRepository.getFavoriteTvShow()
}