package id.ac.mymoviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.ac.mymoviecatalogue.data.FilmRepository
import id.ac.mymoviecatalogue.data.source.local.entity.MoviesEntity
import id.ac.mymoviecatalogue.data.source.local.entity.TvShowEntity
import id.ac.mymoviecatalogue.ui.utils.ResponseFileReader
import id.ac.mymoviecatalogue.vo.Resource
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private lateinit var mockWebServer: MockWebServer
    private val movieId = 460465
    private val showId = 60735

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var movieEntity: MoviesEntity

    @Mock
    private lateinit var showEntity: TvShowEntity

    @Mock
    private lateinit var movieObserver: Observer<Resource<MoviesEntity>>

    @Mock
    private lateinit var showObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = DetailViewModel(filmRepository)
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(showId)

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun readMovieDetailJson() {
        val reader = ResponseFileReader("json/MovieDetailResponse.json")
        assertNotNull(reader.content)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = Resource.success(movieEntity)
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyMovie

        `when`(filmRepository.getDetailMovie(movieId)).thenReturn(movie)

        viewModel.movieDetail.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun readTvShowDetailJson() {
        val reader = ResponseFileReader("json/TvShowDetailResponse.json")
        assertNotNull(reader.content)
    }

    @Test
    fun getShowDetail() {
        val dummyShows = Resource.success(showEntity)
        val show = MutableLiveData<Resource<TvShowEntity>>()
        show.value = dummyShows

        `when`(filmRepository.getDetailTvShow(showId)).thenReturn(show)

        viewModel.showDetail.observeForever(showObserver)
        verify(showObserver).onChanged(dummyShows)
    }
}