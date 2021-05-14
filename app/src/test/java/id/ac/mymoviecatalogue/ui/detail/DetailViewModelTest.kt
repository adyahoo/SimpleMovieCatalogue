package id.ac.mymoviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.data.source.FilmRepository
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig
import id.ac.mymoviecatalogue.data.source.remote.response.MovieDetailResponse
import id.ac.mymoviecatalogue.data.source.remote.response.TvShowDetailResponse
import id.ac.mymoviecatalogue.ui.utils.ResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
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
import java.net.HttpURLConnection

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
    private lateinit var movieObserver: Observer<MovieDetailResponse>

    @Mock
    private lateinit var showObserver: Observer<TvShowDetailResponse>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = DetailViewModel(filmRepository)

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
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(ResponseFileReader("json/MovieDetailResponse.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()

        val dummyMovie = ApiConfig().getApiService().getMovieDetail(movieId, BuildConfig.MOVIE_API_KEY).execute().body()
        val movie = MutableLiveData<MovieDetailResponse>()
        movie.value = dummyMovie

        `when`(filmRepository.getDetailMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovieDetail(movieId).value
        verify(filmRepository).getDetailMovie(movieId)

        val responseObject = mockResponse?.let { getMockedJsonObject(it) }

        assertNotNull(movieEntity)
        if (responseObject != null) {
            with(responseObject) {
                assertEquals(getInt("id"), movieEntity?.id)
                assertEquals(getString("title"), movieEntity?.title)
                assertEquals(getString("release_date"), movieEntity?.releaseDate)
                assertEquals(getString("poster_path"), movieEntity?.posterPath)
                assertEquals(getString("overview"), movieEntity?.overview)
            }
        }

        viewModel.getMovieDetail(movieId).observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun readTvShowDetailJson() {
        val reader = ResponseFileReader("json/TvShowDetailResponse.json")
        assertNotNull(reader.content)
    }

    @Test
    fun getShowDetail() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(ResponseFileReader("json/TvShowDetailResponse.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()

        val dummyShows = ApiConfig().getApiService().getTvShowDetail(showId, BuildConfig.MOVIE_API_KEY).execute().body()
        val show = MutableLiveData<TvShowDetailResponse>()
        show.value = dummyShows

        `when`(filmRepository.getDetailTvShow(showId)).thenReturn(show)
        val showEntity = viewModel.getShowDetail(showId).value
        verify(filmRepository).getDetailTvShow(showId)

        val responseObject = mockResponse?.let { getMockedJsonObject(it) }

        assertNotNull(showEntity)
        if (responseObject != null) {
            with(responseObject) {
                assertEquals(getInt("id"), showEntity?.id)
                assertEquals(getString("name"), showEntity?.name)
                assertEquals(getString("first_air_date"), showEntity?.firstAirDate)
                assertEquals(getString("poster_path"), showEntity?.posterPath)
                assertEquals(getString("overview"), showEntity?.overview)
            }
        }

        viewModel.getShowDetail(showId).observeForever(showObserver)
        verify(showObserver).onChanged(dummyShows)
    }

    private fun getMockedJsonObject(mockResponse: String): JSONObject {
        return JSONObject(mockResponse)
    }
}