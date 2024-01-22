package learn.edu.movieslegacyapp.movieslist.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import learn.edu.movieslegacyapp.databinding.MovieRowPopularBinding
import learn.edu.movieslegacyapp.movieslist.data.remote.MoviesApi.Companion.IMAGE_BASE_URL
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieDTO

//class MovieRecyclerViewAdapter @Inject constructor (
class MovieRecyclerViewAdapter (
    private var movies: List<MovieDTO>?,
//    private val clickListener: MovieClickListener) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {
    ) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieRowPopularBinding = MovieRowPopularBinding.inflate(layoutInflater)
        return MovieViewHolder(movieRowPopularBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies?.get(position)
        holder.bind(movie)
    }
    override fun getItemCount():Int = movies?.size as Int

    class MovieViewHolder(private val binding: MovieRowPopularBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieDTO: MovieDTO?) {
            Glide
                .with(binding.root)
                .load(IMAGE_BASE_URL +  movieDTO?.posterPath)
                .into(binding.moviePoster)
        }
    }

    class MovieClickListener(val clickListener: (movieName: String) -> Unit) {

    }
}