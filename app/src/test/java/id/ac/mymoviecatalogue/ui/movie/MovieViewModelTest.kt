package id.ac.mymoviecatalogue.ui.movie

import id.ac.mymoviecatalogue.data.MoviesEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private var dummyMovies: ArrayList<MoviesEntity>? = null

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(12, movies.size)
    }

    @Test
    fun getNoMovies() {
        assertNull(dummyMovies)
    }
}