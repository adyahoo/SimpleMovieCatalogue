package id.ac.mymoviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.mymoviecatalogue.data.source.FilmRepository
import id.ac.mymoviecatalogue.di.Injection
import id.ac.mymoviecatalogue.ui.detail.DetailViewModel
import id.ac.mymoviecatalogue.ui.movie.MovieViewModel
import id.ac.mymoviecatalogue.ui.show.ShowViewModel

class ViewModelFactory private constructor(private val filmRepository: FilmRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(filmRepository) as T
            }
            modelClass.isAssignableFrom(ShowViewModel::class.java) -> {
                return ShowViewModel(filmRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(filmRepository) as T
            }
            else -> throw Throwable("Unknown View Model Class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository()).apply { instance = this }
                }
    }
}