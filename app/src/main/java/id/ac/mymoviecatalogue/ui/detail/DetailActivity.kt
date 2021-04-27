package id.ac.mymoviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.ac.mymoviecatalogue.R
import id.ac.mymoviecatalogue.data.MoviesEntity
import id.ac.mymoviecatalogue.data.TvShowEntity
import id.ac.mymoviecatalogue.databinding.ActivityDetailBinding
import id.ac.mymoviecatalogue.databinding.ContentDetailBinding
import id.ac.mymoviecatalogue.ui.show.ShowAdapter.Companion.EXTRA_SHOW

class DetailActivity : AppCompatActivity() {
    private lateinit var detailContentBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityBinding.detailContent
        setContentView(activityBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Section"

        lateinit var tvShow: TvShowEntity
        lateinit var movie: MoviesEntity
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        if (intent.getStringExtra(EXTRA_TYPE) == EXTRA_SHOW) {
            val showId = intent.getStringExtra(EXTRA_SHOW_ID).toString()
            tvShow = viewModel.getShowDetail(showId)!!
            populateShow(tvShow)
        } else {
            val movieId = intent.getStringExtra(EXTRA_MOVIE_ID).toString()
            movie = viewModel.getMovieDetail(movieId)!!
            populateMovie(movie)
        }
    }

    private fun populateMovie(movie: MoviesEntity) {
        with(detailContentBinding) {
            tvTitle.text = movie.title
            tvDate.text = movie.releaseDate
            tvGenre.text = movie.genre
            tvDirector.text = movie.director
            tvOverview.text = movie.overview
            Glide.with(this@DetailActivity)
                    .load(movie.poster)
                    .into(ivPoster)
        }
    }

    private fun populateShow(tvShow: TvShowEntity) {
        with(detailContentBinding) {
            tvTitle.text = tvShow.title
            tvDate.text = tvShow.releaseYear
            tvGenre.text = tvShow.genre
            tvDirector.text = tvShow.creator
            tvOverview.text = tvShow.overview
            titleDirector.text = getString(R.string.text_creator)
            Glide.with(this@DetailActivity)
                    .load(tvShow.poster)
                    .into(ivPoster)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    companion object{
        const val EXTRA_SHOW_ID = "show_id"
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_TYPE = "type"
    }
}