package id.ac.mymoviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.R
import id.ac.mymoviecatalogue.data.MoviesEntity
import id.ac.mymoviecatalogue.data.TvShowEntity
import id.ac.mymoviecatalogue.data.source.remote.response.MovieDetailResponse
import id.ac.mymoviecatalogue.data.source.remote.response.TvShowDetailResponse
import id.ac.mymoviecatalogue.databinding.ActivityDetailBinding
import id.ac.mymoviecatalogue.databinding.ContentDetailBinding
import id.ac.mymoviecatalogue.ui.show.ShowAdapter.Companion.EXTRA_SHOW
import id.ac.mymoviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var detailContentBinding: ContentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityBinding.detailContent
        setContentView(activityBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Section"

        detailContentBinding.progbarDetail.visibility = View.VISIBLE
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (intent.getStringExtra(EXTRA_TYPE) == EXTRA_SHOW) {
            populateShow()
        } else {
            populateMovie()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun populateMovie() {
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)

        viewModel.getMovieDetail(movieId).observe(this, { movie ->
            val moviesEntity = parseToMovieEntity(movie)
            with(detailContentBinding) {
                progbarDetail.visibility = View.GONE
                tvTitle.text = moviesEntity.title
                tvDate.text = moviesEntity.releaseDate
                tvOverview.text = moviesEntity.overview
                tvProduction.text = moviesEntity.production
                tvGenre.text = moviesEntity.genre
                Glide.with(this@DetailActivity)
                        .load(BuildConfig.IMAGE_BASE_URL+moviesEntity.poster)
                        .into(ivPoster)
            }
        })
    }

    private fun parseToMovieEntity(movie: MovieDetailResponse): MoviesEntity {
        return MoviesEntity(
            movie.id,
            movie.title,
            movie.releaseDate,
            movie.overview,
            movie.genres.joinToString { it.name },
            movie.productionCompanies.joinToString { it.name },
            movie.posterPath
        )
    }

    private fun populateShow() {
        val showId = intent.getIntExtra(EXTRA_SHOW_ID, 0)

        viewModel.getShowDetail(showId).observe(this, { show ->
            val showEntity = parseToShowEntity(show)
            with(detailContentBinding) {
                progbarDetail.visibility = View.GONE
                tvTitle.text = showEntity.title
                tvDate.text = showEntity.releaseDate
                tvOverview.text = showEntity.overview
                titleProduction.text = getString(R.string.text_creator)
                tvProduction.text = showEntity.creator
                tvGenre.text = showEntity.genre
                Glide.with(this@DetailActivity)
                        .load(BuildConfig.IMAGE_BASE_URL+showEntity.poster)
                        .into(ivPoster)
            }
        })
    }

    private fun parseToShowEntity(show: TvShowDetailResponse): TvShowEntity {
        return TvShowEntity(
            show.id,
            show.name,
            show.firstAirDate,
            show.overview,
            show.createdBy.joinToString { it.name },
            show.genres.joinToString { it.name },
            show.posterPath
        )
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