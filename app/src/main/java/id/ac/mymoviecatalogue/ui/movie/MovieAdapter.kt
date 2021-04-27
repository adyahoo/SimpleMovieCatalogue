package id.ac.mymoviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.mymoviecatalogue.data.MoviesEntity
import id.ac.mymoviecatalogue.databinding.ItemDataBinding
import id.ac.mymoviecatalogue.ui.detail.DetailActivity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovie = ArrayList<MoviesEntity>()
    companion object{
        const val EXTRA_MOVIE = "movie"
    }

    fun setMovie(movies: List<MoviesEntity>) {
        listMovie.clear()
        listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvGenre.text = movie.genre
                Glide.with(itemView.context)
                    .load(movie.poster)
                    .into(civPoster)
            }

            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, DetailActivity::class.java)
//                intent.putExtra(DetailActivity.EXTRA_TYPE, EXTRA_MOVIE)
//                intent.putExtra(DetailActivity.EXTRA_MOVIE_ID, movie.movieId)
//                itemView.context.startActivity(intent)

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
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}