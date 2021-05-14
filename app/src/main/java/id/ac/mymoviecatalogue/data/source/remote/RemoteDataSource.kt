package id.ac.mymoviecatalogue.data.source.remote

import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.data.source.remote.api.ApiConfig
import id.ac.mymoviecatalogue.data.source.remote.response.*
import id.ac.mymoviecatalogue.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiConfig: ApiConfig){

    fun getListMovies(callback: LoadMoviesCallback) {
        val client = apiConfig.getApiService().getListMovies(BuildConfig.MOVIE_API_KEY)
        val moviesList = ArrayList<ResultsItemMovie>()
        EspressoIdlingResources.increment()
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful) {
                    moviesList.addAll(response.body()?.results!!)
                    callback.onAllMoviesReceived(moviesList)
                    EspressoIdlingResources.decrement()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getListTvShows(callback: LoadTvShowsCallback) {
        val client = apiConfig.getApiService().getListTvShows(BuildConfig.MOVIE_API_KEY)
        val showList = ArrayList<ResultsItemTvShow>()
        EspressoIdlingResources.increment()
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    showList.addAll(response.body()?.results!!)
                    callback.onAllTvShowReceived(showList)
                    EspressoIdlingResources.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getDetailMovie(movieId: Int, callback: LoadDetailMovieCallback) {
        val client = apiConfig.getApiService().getMovieDetail(movieId, BuildConfig.MOVIE_API_KEY)
        EspressoIdlingResources.increment()
        client.enqueue(object : Callback<MovieDetailResponse> {
            lateinit var movieDetail: MovieDetailResponse
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                movieDetail = response.body()!!
                callback.onDetailMovieReceived(movieDetail)
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getDetailTvShow(showId: Int, callback: LoadDetailTvShowCallback) {
        val client = apiConfig.getApiService().getTvShowDetail(showId, BuildConfig.MOVIE_API_KEY)
        EspressoIdlingResources.increment()
        client.enqueue(object : Callback<TvShowDetailResponse> {
            lateinit var showDetail: TvShowDetailResponse
            override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                showDetail = response.body()!!
                callback.onDetailTvShowReceived(showDetail)
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponse: List<ResultsItemMovie>?)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowReceived(tvShowResponse: List<ResultsItemTvShow>?)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(movieDetailResponse: MovieDetailResponse?)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(tvShowDetailResponse: TvShowDetailResponse?)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: ApiConfig): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(apiConfig).apply { instance = this }
                }
    }
}