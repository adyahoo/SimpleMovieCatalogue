package id.ac.mymoviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import id.ac.mymoviecatalogue.data.MoviesEntity
import id.ac.mymoviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): ArrayList<MoviesEntity> {
        return DataDummy.getMovies()
    }
}