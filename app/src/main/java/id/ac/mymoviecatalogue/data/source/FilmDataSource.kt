package id.ac.mymoviecatalogue.data.source

import androidx.lifecycle.LiveData
import id.ac.mymoviecatalogue.data.source.remote.response.MovieDetailResponse
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemMovie
import id.ac.mymoviecatalogue.data.source.remote.response.ResultsItemTvShow
import id.ac.mymoviecatalogue.data.source.remote.response.TvShowDetailResponse

interface FilmDataSource {
    fun getListMovies(): LiveData<List<ResultsItemMovie>>

    fun getListTvShow(): LiveData<List<ResultsItemTvShow>>

    fun getDetailMovie(movieId: Int): LiveData<MovieDetailResponse>

    fun getDetailTvShow(showId: Int): LiveData<TvShowDetailResponse>
}