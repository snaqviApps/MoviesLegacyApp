package learn.edu.movieslegacyapp.movieslist.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import learn.edu.movieslegacyapp.R
import learn.edu.movieslegacyapp.databinding.FragmentPopularMoviesBinding
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import learn.edu.movieslegacyapp.movieslist.presentation.view.adapter.MovieRecyclerViewAdapter
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory

/**
 * Handles Popular Movie data (only Poster-view)
 */
class PopularMoviesFragment
//    private val repositoryInFragmentsFactory: MoviesListRepository)           // Fragment constructor doesn't work with Dagger
    : Fragment(R.layout.fragment_popular_movies) {

//    @Inject
    private lateinit var repository: MoviesListRepository

    private lateinit var moviesRecyclerViewAdapter : MovieRecyclerViewAdapter
    private var fragmentPopularMoviesBinding : FragmentPopularMoviesBinding? = null

    private lateinit var moviesListViewModel: MoviesListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = MoviesListRepository()
        val moviesListViewModelFactory = MoviesListViewModelFactory(repository, true)
        moviesListViewModel = ViewModelProvider(this,  moviesListViewModelFactory)[MoviesListViewModel::class.java]

        val binding = FragmentPopularMoviesBinding.bind(view)
        fragmentPopularMoviesBinding = binding
        setupObserver(moviesListViewModel, binding)
    }

    private fun setupObserver(
        moviesListViewModel: MoviesListViewModel,
        binding: FragmentPopularMoviesBinding
    ) {
//        moviesListViewModel.moviesState.observe(viewLifecycleOwner, Observer { uIState ->         // optimized below
        moviesListViewModel.moviesState.observe(viewLifecycleOwner) { uIState ->
            when (uIState) {
                is MoviesListRepository.UIState.EmptyState -> {}
                is MoviesListRepository.UIState.SuccessState -> {
                    val movies = uIState.movieListDTO
                    val sortedMoviesList = movies?.results?.sortedWith(compareBy { it.popularity })       // sorted per popularity

                    // passing data to Popular-MovieAdapter
                    moviesRecyclerViewAdapter = MovieRecyclerViewAdapter(sortedMoviesList)
                    binding.rViewPopularMovies.adapter = moviesRecyclerViewAdapter
                    binding.rViewPopularMovies.layoutManager = GridLayoutManager(requireContext(), 2)

                    Log.d("mLogs", "movies pages: ${movies?.totalPages}")
                }

                is MoviesListRepository.UIState.ErrorState -> {
                    Toast.makeText(activity, "Error: ${uIState.error}", Toast.LENGTH_LONG).show()
                    Log.d("mLogs", "Error: ${uIState.error}")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentPopularMoviesBinding = null
    }

}