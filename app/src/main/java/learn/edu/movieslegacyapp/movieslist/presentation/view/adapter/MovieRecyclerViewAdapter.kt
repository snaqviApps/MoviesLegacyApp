package learn.edu.movieslegacyapp.movieslist.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import learn.edu.movieslegacyapp.databinding.MovieRowPopularBinding
import learn.edu.movieslegacyapp.movieslist.data.remote.MoviesApi.Companion.IMAGE_BASE_URL
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieDTO
import javax.inject.Inject

class MovieRecyclerViewAdapter @Inject constructor(
    private var movies: List<MovieDTO>?,
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    private var imageClickListener: ((String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieRowPopularBinding = MovieRowPopularBinding.inflate(layoutInflater)
        return MovieViewHolder(movieRowPopularBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies?.get(position)
        holder.bind(movie, imageClickListener)
    }

    override fun getItemCount(): Int = movies?.size as Int
    fun setOnImageClickListener(listener: (String) -> Unit) {
        imageClickListener = listener
    }

    class MovieViewHolder(private val binding: MovieRowPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movieDTO: MovieDTO?,
            imageClickListener: ((String) -> Unit)?
        ) {
            Glide
                .with(binding.root)
                .load(IMAGE_BASE_URL + movieDTO?.posterPath)
                .into(binding.moviePoster)
            binding.moviePoster.setOnClickListener {
                imageClickListener?.let {
                    it(movieDTO?.backdropPath ?: "")
                }
            }
        }
    }

}