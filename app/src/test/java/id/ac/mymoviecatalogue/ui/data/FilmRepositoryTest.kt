package id.ac.mymoviecatalogue.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.data.source.remote.RemoteDataSource
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig
import id.ac.mymoviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

import org.mockito.Mockito.mock

class FilmRepositoryTest {
    private val remote = mock(RemoteDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote)

    private val movieId = 460465
    private val showId = 60735

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun getListMovies() {
        val listMovies = ApiConfig().getApiService().getListMovies(BuildConfig.MOVIE_API_KEY).execute().body()?.results

        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(listMovies)
            null
        }.`when`(remote).getListMovies(any())

        val movieEntity = LiveDataTestUtil.getValue(filmRepository.getListMovies())
        verify(remote).getListMovies(any())

        assertNotNull(movieEntity)
        assertEquals(listMovies?.size, movieEntity.size)
    }

    @Test
    fun getListTvShow() {
        val listShows = ApiConfig().getApiService().getListTvShows(BuildConfig.MOVIE_API_KEY).execute().body()?.results

        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowReceived(listShows)
            null
        }.`when`(remote).getListTvShows(any())

        val showEntity = LiveDataTestUtil.getValue(filmRepository.getListTvShow())
        verify(remote).getListTvShows(any())

        assertNotNull(showEntity)
        assertEquals(listShows?.size, showEntity.size)
    }

    @Test
    fun getDetailMovie() {
        val movie = ApiConfig().getApiService().getMovieDetail(movieId, BuildConfig.MOVIE_API_KEY).execute().body()

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieReceived(movie)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())

        val detailMovieEntity = LiveDataTestUtil.getValue(filmRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(eq(movieId), any())

        assertNotNull(detailMovieEntity)
        assertEquals(movie?.title, detailMovieEntity.title)
    }

    @Test
    fun getDetailTvShow() {
        val show = ApiConfig().getApiService().getTvShowDetail(showId, BuildConfig.MOVIE_API_KEY).execute().body()

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShowCallback).onDetailTvShowReceived(show)
            null
        }.`when`(remote).getDetailTvShow(eq(showId), any())

        val detailShowEntity = LiveDataTestUtil.getValue(filmRepository.getDetailTvShow(showId))
        verify(remote).getDetailTvShow(eq(showId), any())

        assertNotNull(detailShowEntity)
        assertEquals(show?.name, detailShowEntity.name)
    }
}