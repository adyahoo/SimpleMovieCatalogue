package id.ac.mymoviecatalogue.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.mymoviecatalogue.data.source.FilmDataSource
import id.ac.mymoviecatalogue.data.source.remote.RemoteDataSource
import id.ac.mymoviecatalogue.data.source.remote.response.*

class FakeFilmRepository (private val remoteDataSource: RemoteDataSource): FilmDataSource {
    override fun getListMovies(): LiveData<List<ResultsItemMovie>> {
        val moviesResult = MutableLiveData<List<ResultsItemMovie>>()
        remoteDataSource.getListMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse: List<ResultsItemMovie>?) {
                if (moviesResponse != null) {
                    moviesResult.postValue(moviesResponse)
                }
            }
        })
        return moviesResult
    }

    override fun getListTvShow(): LiveData<List<ResultsItemTvShow>> {
        val tvShowResult = MutableLiveData<List<ResultsItemTvShow>>()
        remoteDataSource.getListTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<ResultsItemTvShow>?) {
                if (tvShowResponse != null) {
                    tvShowResult.postValue(tvShowResponse)
                }
            }
        })

        return tvShowResult
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieDetailResponse> {
        val detailMovieResult = MutableLiveData<MovieDetailResponse>()
        remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(movieDetailResponse: MovieDetailResponse?) {
                detailMovieResult.postValue(movieDetailResponse)
            }
        })

        return detailMovieResult
    }

    override fun getDetailTvShow(showId: Int): LiveData<TvShowDetailResponse> {
        val detailTvShowResult = MutableLiveData<TvShowDetailResponse>()
        remoteDataSource.getDetailTvShow(
            showId,
            object : RemoteDataSource.LoadDetailTvShowCallback {
                override fun onDetailTvShowReceived(tvShowDetailResponse: TvShowDetailResponse?) {
                    detailTvShowResult.postValue(tvShowDetailResponse)
                }
            })

        return detailTvShowResult
    }
}