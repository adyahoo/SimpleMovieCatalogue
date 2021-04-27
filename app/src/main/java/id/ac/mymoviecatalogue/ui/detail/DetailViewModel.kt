package id.ac.mymoviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import id.ac.mymoviecatalogue.data.MoviesEntity
import id.ac.mymoviecatalogue.data.TvShowEntity
import id.ac.mymoviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {

    fun getMovieDetail(movieId: String): MoviesEntity? {
//        lateinit var movieDetail: MoviesEntity
//        val movies = DataDummy.getMovies()
//        for (movie in movies) {
//            if (movie.movieId == movieId) {
//                movieDetail = movie
//                break
//            }
//        }
//        return movieDetail

        return DataDummy.getMovies().find {
            movieId == it.movieId
        }
    }

    fun getShowDetail(showId: String): TvShowEntity? {
//        lateinit var showDetail: ShowEntity
//        val shows = DataDummy.getShows()
//        for (show in shows) {
//            if (show.showId == showId) {
//                showDetail = show
//                break
//            }
//        }
//        return showDetail

        return DataDummy.getShows().find {
            showId == it.showId
        }
    }
}