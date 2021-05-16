package id.ac.mymoviecatalogue.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.data.source.local.LocalDataSource
import id.ac.mymoviecatalogue.data.source.local.entity.MoviesEntity
import id.ac.mymoviecatalogue.data.source.local.entity.TvShowEntity
import id.ac.mymoviecatalogue.data.source.remote.RemoteDataSource
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig
import id.ac.mymoviecatalogue.ui.utils.PagedListUtils
import id.ac.mymoviecatalogue.utils.AppExecutors
import id.ac.mymoviecatalogue.vo.Resource
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`

import org.mockito.Mockito.mock

class FilmRepositoryTest {
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    private val movieId = 460465
    private val showId = 60735

    @get:Rule
    var instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun getListMovies() {
        val listMovies = ApiConfig().getApiService().getListMovies(BuildConfig.MOVIE_API_KEY).execute().body()?.results

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        filmRepository.getListMovies()

        val movies = Resource.success(PagedListUtils.mockedPagedList(listMovies!!)).data
        verify(local).getAllMovies()
        assertNotNull(movies)
        assertEquals(listMovies.size, movies?.size)
    }

    @Test
    fun getListTvShow() {
        val listShows = ApiConfig().getApiService().getListTvShows(BuildConfig.MOVIE_API_KEY).execute().body()?.results

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getListTvShow()

        val shows = Resource.success(PagedListUtils.mockedPagedList(listShows!!)).data
        verify(local).getAllTvShows()
        assertNotNull(shows)
        assertEquals(listShows.size, shows?.size)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<MoviesEntity>()
        val detailMovieEntity = filmRepository.getDetailMovie(movieId)
        dummyDetailMovie.value = detailMovieEntity.value?.data

        `when`(local.getMovieById(movieId)).thenReturn(dummyDetailMovie)

        verify(local).getMovieById(movieId)

        assertNotNull(dummyDetailMovie)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailShow = MutableLiveData<TvShowEntity>()
        val detailShowEntity = filmRepository.getDetailTvShow(showId)
        dummyDetailShow.value = detailShowEntity.value?.data

        `when`(local.getTvShowById(showId)).thenReturn(dummyDetailShow)
        verify(local).getTvShowById(showId)

        assertNotNull(dummyDetailShow)
    }

    @Test
    fun getFavoriteMovies() {
        val listMovies = ApiConfig().getApiService().getListMovies(BuildConfig.MOVIE_API_KEY).execute().body()?.results

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteMovies()

        val favMovies = Resource.success(PagedListUtils.mockedPagedList(listMovies!!)).data
        verify(local).getFavoriteMovies()
        assertNotNull(favMovies)
        assertEquals(listMovies.size, favMovies?.size)
    }

    @Test
    fun getFavoriteTvShows() {
        val listShows = ApiConfig().getApiService().getListTvShows(BuildConfig.MOVIE_API_KEY).execute().body()?.results

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteTvShow()

        val favShows = Resource.success(PagedListUtils.mockedPagedList(listShows!!)).data
        verify(local).getFavoriteTvShows()
        assertNotNull(favShows)
        assertEquals(listShows.size, favShows?.size)
    }
}