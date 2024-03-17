package learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import learn.edu.movieslegacyapp.R
import learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory

/**
 * Handles Popular Movie data (only Poster-view)
 */
class PopularMoviesFragment : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var moviesListViewModel: MoviesListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = MoviesListRepository()
        val moviesListViewModelFactory = MoviesListViewModelFactory(repository)
        moviesListViewModel = ViewModelProvider(this,  moviesListViewModelFactory)[MoviesListViewModel::class.java]
        setupObserver(moviesListViewModel)
        setupAdapter()
    }

    private fun setupAdapter() {
        // TODO("Not yet implemented")
    }

    private fun setupObserver(moviesListViewModel: MoviesListViewModel) {
//        moviesListViewModel.moviesState.observe(viewLifecycleOwner, Observer { uIState ->         // optimized below
//        moviesListViewModel.moviesState.observe(viewLifecycleOwner, { uIState ->                  // needs to move 'lambda' out of parentheses
        moviesListViewModel.moviesState.observe(viewLifecycleOwner) { uIState ->
            when (uIState) {
                is MoviesListRepository.UIState.EmptyState -> {}
                is MoviesListRepository.UIState.SuccessState -> {
                    val movies = uIState.movieListDTO
                    Toast.makeText(
                        activity,
                        "MoviesList: ${movies?.results?.toList()}",
                        Toast.LENGTH_LONG
                    ).show()
                    val sortedMoviesList =
                        movies?.results?.sortedWith(compareBy { it.popularity })       // sorted per popularity
                    Log.d("mLogs", "movies pages: ${movies?.totalPages}")
                }

                is MoviesListRepository.UIState.ErrorState -> {
                    Toast.makeText(activity, "Error: ${uIState.error}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}