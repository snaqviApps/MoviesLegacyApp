package learn.edu.movieslegacyapp.movieslist.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import learn.edu.movieslegacyapp.R
import learn.edu.movieslegacyapp.databinding.FragmentUpcomingMoviesBinding
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import learn.edu.movieslegacyapp.movieslist.presentation.view.adapter.MovieRecyclerViewAdapter
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory

class UpComingMoviesFragment : Fragment(R.layout.fragment_upcoming_movies) {

    private lateinit var moviesRecyclerViewAdapter : MovieRecyclerViewAdapter
    private var fragmentUpcomingMoviesBinding: FragmentUpcomingMoviesBinding? = null
    private lateinit var moviesListViewModel: MoviesListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesListViewModelFactory = MoviesListViewModelFactory(false)
        moviesListViewModel = ViewModelProvider(this,  moviesListViewModelFactory)[MoviesListViewModel::class.java]
        val binding = FragmentUpcomingMoviesBinding.bind(view)
        fragmentUpcomingMoviesBinding = binding
        setupObserver(moviesListViewModel, binding)
    }

    private fun setupObserver(
        moviesListViewModel: MoviesListViewModel,
        binding: FragmentUpcomingMoviesBinding
    ) {
        moviesListViewModel.moviesState.observe(viewLifecycleOwner) { uIState ->
            when (uIState) {
                is MoviesListRepository.UIState.EmptyState -> {}
                is MoviesListRepository.UIState.SuccessState -> {
                    val upComingMovies = uIState.movieListDTO

                    // passing data to Upcoming-MovieAdapter
                    upComingMovies?.let {
                        moviesRecyclerViewAdapter = MovieRecyclerViewAdapter(it.results)
                        binding.rViewUpcomingMovies.adapter = moviesRecyclerViewAdapter
                        binding.rViewUpcomingMovies.layoutManager = GridLayoutManager(requireContext(), 2)
                        Log.d("mLogs", "upcoming movies pages: ${it.totalPages}")
                    }
                }
                is MoviesListRepository.UIState.ErrorState -> {
                    Toast.makeText(activity, "Error: ${uIState.error}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentUpcomingMoviesBinding = null
    }
}