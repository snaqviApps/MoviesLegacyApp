package learn.edu.movieslegacyapp.movieslist.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import learn.edu.movieslegacyapp.R
import learn.edu.movieslegacyapp.databinding.FragmentUpcomingMoviesBinding
import learn.edu.movieslegacyapp.movieslist.presentation.view.adapter.MovieRecyclerViewAdapter
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory
import learn.edu.movieslegacyapp.movieslist.util.UIState
import javax.inject.Inject

class UpComingMoviesFragment @Inject constructor() : Fragment(R.layout.fragment_upcoming_movies) {

    private lateinit var moviesRecyclerViewAdapter: MovieRecyclerViewAdapter
    private var fragmentUpcomingMoviesBinding: FragmentUpcomingMoviesBinding? = null
    private lateinit var moviesListViewModel: MoviesListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesListViewModelFactory = MoviesListViewModelFactory(false)
        moviesListViewModel =
            ViewModelProvider(this, moviesListViewModelFactory)[MoviesListViewModel::class.java]
        val binding = FragmentUpcomingMoviesBinding.bind(view)
        fragmentUpcomingMoviesBinding = binding
        setupObserver(moviesListViewModel, binding)
    }

    private fun setupObserver(
        moviesListViewModel: MoviesListViewModel,
        binding: FragmentUpcomingMoviesBinding
    ) {
        moviesListViewModel.moviesUIState.observe(viewLifecycleOwner) { uIState ->
            when (uIState) {
                is UIState.EmptyState -> {}
                is UIState.SuccessState -> {
                    val upComingMovies = uIState.movieListDTO
                    upComingMovies?.let { moviesReceived ->
                        moviesRecyclerViewAdapter = MovieRecyclerViewAdapter(moviesReceived.results)
                        binding.rViewUpcomingMovies.adapter = moviesRecyclerViewAdapter
                        binding.rViewUpcomingMovies.layoutManager =
                            GridLayoutManager(requireContext(), 2)
                        moviesRecyclerViewAdapter.apply {
                            setOnImageClickListener {
                                findNavController().navigate(
                                    UpComingMoviesFragmentDirections.actionUpComingMoviesFragmentToDetailsMovieFragment()
                                )
                            }
                        }
                        Log.d("mLogs", "upcoming movies pages: ${moviesReceived.totalPages}")
                    }
                }

                is UIState.ErrorState -> {
                    Toast.makeText(activity, "Error: ${uIState.error}", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentUpcomingMoviesBinding = null
    }
}