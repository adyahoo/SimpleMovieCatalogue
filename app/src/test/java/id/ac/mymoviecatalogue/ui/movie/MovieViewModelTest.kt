package id.ac.mymoviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.nhaarman.mockitokotlin2.verify
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.data.FilmRepository
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemMovie
import id.ac.mymoviecatalogue.ui.utils.ResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsItemMovie>>

    @Mock
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = MovieViewModel(filmRepository)

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun readJson() {
        val reader = ResponseFileReader("json/MoviesResponses.json")
        assertNotNull(reader.content)
    }

    @Test
    fun getMovies() {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(ResponseFileReader("json/MoviesResponses.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()

        val dummyMovies = ApiConfig().getApiService().getListMovies(BuildConfig.MOVIE_API_KEY).execute().body()?.results
        val movies = MutableLiveData<List<ResultsItemMovie>>()
        movies.value = dummyMovies

        `when`(filmRepository.getListMovies()).thenReturn(movies)
        val moviesEntity = viewModel.getMovies().value
        verify<FilmRepository>(filmRepository).getListMovies()

        assertNotNull(moviesEntity)
        assertEquals(mockResponse?.let { getMockedJsonSize(it) }, moviesEntity?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    private fun getMockedJsonSize(mockResponse: String): Int {
        val reader = JSONObject(mockResponse)
        return reader.getJSONArray("results").length()
    }
}