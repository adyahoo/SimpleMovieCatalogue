package id.ac.mymoviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.R
import id.ac.mymoviecatalogue.databinding.ActivityDetailBinding
import id.ac.mymoviecatalogue.databinding.ContentDetailBinding
import id.ac.mymoviecatalogue.ui.show.ShowAdapter.Companion.EXTRA_SHOW
import id.ac.mymoviecatalogue.viewmodel.ViewModelFactory
import id.ac.mymoviecatalogue.vo.Status

class DetailActivity : AppCompatActivity() {
    private lateinit var detailContentBinding: ContentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityBinding.detailContent
        setContentView(activityBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Section"

        detailContentBinding.progbarDetail.visibility = View.VISIBLE
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (intent.getStringExtra(EXTRA_TYPE) == EXTRA_SHOW) {
            populateShow()
        } else {
            populateMovie()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.movieDetail.observe(this, { movieDetail ->
            if (movieDetail != null) {
                when (movieDetail.status) {
                    Status.LOADING -> detailContentBinding.progbarDetail.visibility = View.VISIBLE
                    Status.SUCCESS -> if (movieDetail.data != null) {
                        detailContentBinding.progbarDetail.visibility = View.GONE
                        val state = movieDetail.data.isFavorite
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        detailContentBinding.progbarDetail.visibility = View.GONE
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        viewModel.showDetail.observe(this, { showDetail ->
            if (showDetail != null) {
                when (showDetail.status) {
                    Status.LOADING -> detailContentBinding.progbarDetail.visibility = View.VISIBLE
                    Status.SUCCESS -> if (showDetail.data != null) {
                        detailContentBinding.progbarDetail.visibility = View.GONE
                        val state = showDetail.data.isFavorite
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        detailContentBinding.progbarDetail.visibility = View.GONE
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> viewModel.setFavorite()
            android.R.id.home -> finish()
        }
        return true
    }

    private fun setFavoriteState(state: Boolean) {
        if(menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun populateMovie() {
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        viewModel.setSelectedMovie(movieId)
        viewModel.movieDetail.observe(this, { movie ->
            if (movie != null) {
                when (movie.status) {
                    Status.LOADING -> detailContentBinding.progbarDetail.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        detailContentBinding.progbarDetail.visibility = View.GONE
                        with(detailContentBinding) {
                            progbarDetail.visibility = View.GONE
                            tvTitle.text = movie.data?.title
                            tvDate.text = movie.data?.releaseDate
                            tvOverview.text = movie.data?.overview
                            tvProduction.text = movie.data?.production
                            tvGenre.text = movie.data?.genre
                            Glide.with(this@DetailActivity)
                                    .load(BuildConfig.IMAGE_BASE_URL + movie.data?.poster)
                                    .into(ivPoster)
                        }
                    }
                    Status.ERROR -> {
                        detailContentBinding.progbarDetail.visibility = View.GONE
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun populateShow() {
        val showId = intent.getIntExtra(EXTRA_SHOW_ID, 0)
        viewModel.setSelectedTvShow(showId)

        viewModel.showDetail.observe(this, { show ->
            if (show != null) {
                when (show.status) {
                    Status.LOADING -> detailContentBinding.progbarDetail.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        with(detailContentBinding) {
                            progbarDetail.visibility = View.GONE
                            tvTitle.text = show.data?.title
                            tvDate.text = show.data?.releaseDate
                            tvOverview.text = show.data?.overview
                            titleProduction.text = getString(R.string.text_creator)
                            tvProduction.text = show.data?.creator
                            tvGenre.text = show.data?.genre
                            Glide.with(this@DetailActivity)
                                .load(BuildConfig.IMAGE_BASE_URL + show.data?.poster)
                                .into(ivPoster)
                        }
                    }
                    Status.ERROR -> {
                        detailContentBinding.progbarDetail.visibility = View.GONE
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    companion object{
        const val EXTRA_SHOW_ID = "show_id"
        const val EXTRA_MOVIE_ID = "movie_id"
        const val EXTRA_TYPE = "type"
    }
}