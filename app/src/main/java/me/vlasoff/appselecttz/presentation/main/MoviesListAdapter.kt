package me.vlasoff.appselecttz.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import me.vlasoff.appselecttz.databinding.MovieRecyclerItemBinding
import me.vlasoff.appselecttz.domain.model.MovieUiModel

class MovieListAdapter :
    PagingDataAdapter<MovieUiModel, MovieListAdapter.MoviesViewHolder>(MovieDiffCallBack) {
    class MoviesViewHolder(
        private val binding: MovieRecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUiModel) {
            binding.movieThumbnail.load(movie.src)
            binding.movieTitle.text = movie.title
            binding.movieDescription.text = movie.description
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(
            getItem(position)
                ?: MovieUiModel(
                    title = "Не найдено",
                    description = "Не найдено",
                    src = "https://activefisher.net/wp-content/uploads/8/c/5/8c5731b80c62c0bb1b42da86f5963c23.jpg"
                )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            MovieRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
}

object MovieDiffCallBack : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem == newItem
    }

}