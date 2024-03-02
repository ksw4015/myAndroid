package com.sample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.data.model.movies.Movie
import com.sample.presentation.databinding.ItemMovieBinding
import java.util.Collections

class MoviesRecyclerAdapter(
    private val mListener: MovieClickListener
) : ListAdapter<Movie, MoviesRecyclerAdapter.MovieViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun moveItem(from: Int, to: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, from, to)
        submitList(newList)
    }

    fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val listener: MovieClickListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                movieTitle.text = movie.movieNm
                movieRank.text = "${movie.rank} (${movie.rankOldAndNew})"
                movieSales.text = "${movie.salesAcc}원"
                movieAudi.text = "${movie.audiAcc}명"
                root.setOnClickListener {
                    listener.onClick(movie.movieCd)
                }
            }
        }

        fun setAlpha(alpha: Float) {
            with(binding) {
                root.alpha = alpha
            }
        }
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.movieCd == newItem.movieCd
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }

        fun interface MovieClickListener {
            fun onClick(movieCd: String)
        }
    }
}