package id.ac.mymoviecatalogue.ui.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.mymoviecatalogue.data.source.FilmRepository
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemTvShow

class ShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getShows(): LiveData<List<ResultsItemTvShow>> = filmRepository.getListTvShow()
}