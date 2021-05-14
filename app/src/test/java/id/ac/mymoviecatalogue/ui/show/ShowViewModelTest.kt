package id.ac.mymoviecatalogue.ui.show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.data.source.FilmRepository
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemTvShow
import id.ac.mymoviecatalogue.ui.utils.ResponseFileReader
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class ShowViewModelTest {
    private lateinit var viewModel: ShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsItemTvShow>>

    @Mock
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = ShowViewModel(filmRepository)

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun readJson() {
        val reader = ResponseFileReader("json/TvShowsResponses.json")
        assertNotNull(reader.content)
    }

    @Test
    fun getShows() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(ResponseFileReader("json/TvShowsResponses.json").content)
        mockWebServer.enqueue(response)
        val mockRespose = response.getBody()?.readUtf8()

        val dummyShows = ApiConfig().getApiService().getListTvShows(BuildConfig.MOVIE_API_KEY).execute().body()?.results
        val shows = MutableLiveData<List<ResultsItemTvShow>>()
        shows.value = dummyShows

        `when`(filmRepository.getListTvShow()).thenReturn(shows)
        val showEntity = viewModel.getShows().value
        verify<FilmRepository>(filmRepository).getListTvShow()

        assertNotNull(showEntity)
        assertEquals(mockRespose?.let { getMockedJsonSize(it) }, showEntity?.size)

        viewModel.getShows().observeForever(observer)
        verify(observer).onChanged(dummyShows)
    }

    private fun getMockedJsonSize(mockResponse: String): Int {
        val reader = JSONObject(mockResponse)
        return reader.getJSONArray("results").length()
    }
}