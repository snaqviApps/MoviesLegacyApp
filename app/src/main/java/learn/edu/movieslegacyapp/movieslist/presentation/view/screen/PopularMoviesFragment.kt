package learn.edu.movieslegacyapp.movieslist.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import learn.edu.movieslegacyapp.R
import learn.edu.movieslegacyapp.databinding.FragmentPopularMoviesBinding
import learn.edu.movieslegacyapp.movieslist.presentation.view.adapter.MovieRecyclerViewAdapter
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory
import learn.edu.movieslegacyapp.movieslist.util.UIState
import javax.inject.Inject


/**
 * Handles Popular Movie data (only Poster-view)
 */
class PopularMoviesFragment @Inject constructor(
//class PopularMoviesFragment @Inject constructor()
) : Fragment(R.layout.fragment_popular_movies) {


    private lateinit var moviesRecyclerViewAdapter: MovieRecyclerViewAdapter
    private var fragmentPopularMoviesBinding: FragmentPopularMoviesBinding? = null

    private lateinit var moviesListViewModel: MoviesListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesListViewModelFactory = MoviesListViewModelFactory(true)
        moviesListViewModel = ViewModelProvider(this, moviesListViewModelFactory)[MoviesListViewModel::class.java]
        val binding = FragmentPopularMoviesBinding.bind(view)
        fragmentPopularMoviesBinding = binding
        setupObserver(moviesListViewModel, binding)
    }

    private fun setupObserver(
        moviesListViewModel: MoviesListViewModel,
        binding: FragmentPopularMoviesBinding
    ) {
        moviesListViewModel.moviesUIState.observe(viewLifecycleOwner, Observer { uIState ->
            when (uIState) {
                is UIState.EmptyState -> {}
                is UIState.SuccessState -> {
                    val movies = uIState.movieListDTO
                    val sortedMoviesList = movies?.results?.sortedWith(compareBy { it.popularity })       // sorted per popularity
                    moviesRecyclerViewAdapter = MovieRecyclerViewAdapter(sortedMoviesList)                // passing data to MovieAdapter
                    binding.rViewPopularMovies.adapter = moviesRecyclerViewAdapter
                    binding.rViewPopularMovies.layoutManager = GridLayoutManager(requireContext(), 2)
                    moviesRecyclerViewAdapter.apply {
                        setOnImageClickListener {
                            findNavController().navigate(
                                PopularMoviesFragmentDirections.actionPopularMoviesFragmentToDetailsMovieFragment()
                            )
                        }
                    }
                    Log.d("mLogs", "movies pages: ${movies?.totalPages}")
                }

                is UIState.ErrorState -> {
                    Toast.makeText(activity, "Error: ${uIState.error}", Toast.LENGTH_LONG).show()
                    Log.d("mLogs", "Error: ${uIState.error}")
                }
            }
         })

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentPopularMoviesBinding = null
    }

}