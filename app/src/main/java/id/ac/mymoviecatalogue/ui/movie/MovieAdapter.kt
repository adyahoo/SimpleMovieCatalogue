package id.ac.mymoviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.mymoviecatalogue.BuildConfig
import id.ac.mymoviecatalogue.R
import id.ac.mymoviecatalogue.data.source.local.entity.MoviesEntity
import id.ac.mymoviecatalogue.databinding.ItemDataBinding
import id.ac.mymoviecatalogue.ui.detail.DetailActivity

class MovieAdapter: PagedListAdapter<MoviesEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    inner class MovieViewHolder(private val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity) {
            val moviePoster = BuildConfig.IMAGE_BASE_URL+movie.poster
            with(binding) {
                tvTitle.text = movie.title
                tvDate.text = itemView.context.getString(R.string.release_date_rv, movie.releaseDate)
                Glide.with(itemView.context)
                    .load(moviePoster)
                    .into(civPoster)
            }

            itemView.setOnClickListener {

                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_TYPE, EXTRA_MOVIE)
                    putExtra(DetailActivity.EXTRA_MOVIE_ID, movie.movieId)
                }
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as MoviesEntity)
    }

    companion object{
        const val EXTRA_MOVIE = "movie"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}