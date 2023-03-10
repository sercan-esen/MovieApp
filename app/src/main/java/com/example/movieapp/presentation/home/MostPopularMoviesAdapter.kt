package com.example.movieapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.model.response.MoviesItem
import com.example.movieapp.databinding.HomeMostPopularRowBinding
import com.example.movieapp.presentation.util.CategoriesHelper
import com.example.movieapp.presentation.util.extension.loadFromWeb

class MostPopularMoviesAdapter(
    private val context: Context,
    private val listener: (MoviesItem) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    private var moviesList: List<MoviesItem> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeMostPopularRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = moviesList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val movieItem = moviesList[position]
        holder.rootView
        holder.rootView.apply {
            ivMostPopular.loadFromWeb(BuildConfig.FTP_SERVER_ROOT_URL + movieItem.poster_path)
            tvTitleMovie.text = movieItem.title
            tvRatingMostPopular.text = movieItem.vote_average.toString()
            tvCategory.text = CategoriesHelper.findCategoriesNameById(movieItem.genre_ids.firstOrNull())

            root.setOnClickListener {
                listener.invoke(movieItem)
            }
        }
    }

    fun updateList(newList: List<MoviesItem>) {
        moviesList = newList
        notifyDataSetChanged()
    }
}


class ViewHolder(view: HomeMostPopularRowBinding) :
    RecyclerView.ViewHolder(view.root) {
    val rootView = view
}



