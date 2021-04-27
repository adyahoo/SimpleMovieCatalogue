package id.ac.mymoviecatalogue.ui.detail

import id.ac.mymoviecatalogue.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.getMovies()[0]
    private val dummyShows = DataDummy.getShows() [0]

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getMovieDetail() {
        val movie = viewModel.getMovieDetail(dummyMovies.movieId)
        assertNotNull(movie)
        assertEquals(dummyMovies.movieId, movie.movieId)
        assertEquals(dummyMovies.title, movie.title)
        assertEquals(dummyMovies.releaseDate, movie.releaseDate)
        assertEquals(dummyMovies.genre, movie.genre)
        assertEquals(dummyMovies.director, movie.director)
        assertEquals(dummyMovies.poster, movie.poster)
        assertEquals(dummyMovies.overview, movie.overview)
    }

    @Test
    fun getShowDetail() {
        val show = viewModel.getShowDetail(dummyShows.showId)
        assertNotNull(show)
        assertEquals(dummyShows.showId, show.showId)
        assertEquals(dummyShows.title, show.title)
        assertEquals(dummyShows.releaseYear, show.releaseYear)
        assertEquals(dummyShows.genre, show.genre)
        assertEquals(dummyShows.creator, show.creator)
        assertEquals(dummyShows.poster, show.poster)
        assertEquals(dummyShows.overview, show.overview)
    }
}