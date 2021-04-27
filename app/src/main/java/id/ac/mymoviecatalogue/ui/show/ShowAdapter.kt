package id.ac.mymoviecatalogue.ui.show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.mymoviecatalogue.data.TvShowEntity
import id.ac.mymoviecatalogue.databinding.ItemDataBinding
import id.ac.mymoviecatalogue.ui.detail.DetailActivity

class ShowAdapter: RecyclerView.Adapter<ShowAdapter.ShowViewHolder>() {
    private var listShows = ArrayList<TvShowEntity>()
    companion object{
        const val EXTRA_SHOW = "show"
    }

    fun setShow(tvShow: List<TvShowEntity>) {
        if(tvShow == null) return
        listShows.clear()
        listShows.addAll(tvShow)
        notifyDataSetChanged()
    }

    inner class ShowViewHolder(private val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.title
                tvGenre.text = tvShow.genre
                Glide.with(itemView.context)
                    .load(tvShow.poster)
                    .into(civPoster)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TYPE, EXTRA_SHOW)
                intent.putExtra(DetailActivity.EXTRA_SHOW_ID, tvShow.showId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val itemBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(listShows[position])
    }

    override fun getItemCount(): Int {
        return listShows.size
    }
}