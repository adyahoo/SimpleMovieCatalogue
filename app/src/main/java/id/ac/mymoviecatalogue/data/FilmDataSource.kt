package id.ac.mymoviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.ac.mymoviecatalogue.data.source.local.entity.MoviesEntity
import id.ac.mymoviecatalogue.data.source.local.entity.TvShowEntity
import id.ac.mymoviecatalogue.vo.Resource

interface FilmDataSource {
    fun getListMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>>

    fun getListTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getDetailMovie(movieId: Int): LiveData<Resource<MoviesEntity>>

    fun getDetailTvShow(showId: Int): LiveData<Resource<TvShowEntity>>

    fun setFavoriteMovie(movie: MoviesEntity, state: Boolean)

    fun setFavoriteTvShow(show: TvShowEntity, state: Boolean)
}