package learn.edu.movieslegacyapp.movieslist.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import learn.edu.movieslegacyapp.R
import learn.edu.movieslegacyapp.databinding.FragmentUpcomingMoviesBinding
import learn.edu.movieslegacyapp.movieslist.presentation.view.adapter.MovieRecyclerViewAdapter
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory
import learn.edu.movieslegacyapp.movieslist.util.UIState

class UpComingMoviesFragment : Fragment(R.layout.fragment_upcoming_movies) {

    private lateinit var navController: NavController

    private lateinit var moviesRecyclerViewAdapter : MovieRecyclerViewAdapter
    private var fragmentUpcomingMoviesBinding: FragmentUpcomingMoviesBinding? = null
    private lateinit var moviesListViewModel: MoviesListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        navController = navHostFragment.navController

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
        moviesListViewModel.moviesUIState.observe(viewLifecycleOwner) { uIState ->
            when (uIState) {
                is UIState.EmptyState -> {}
                is UIState.SuccessState -> {
                    val upComingMovies = uIState.movieListDTO

                    // passing data to MovieAdapter
                    upComingMovies?.let { moviesReceived ->
                        moviesRecyclerViewAdapter = MovieRecyclerViewAdapter(moviesReceived.results)
                        binding.rViewUpcomingMovies.adapter = moviesRecyclerViewAdapter
                        binding.rViewUpcomingMovies.layoutManager = GridLayoutManager(requireContext(), 2)

                        moviesRecyclerViewAdapter.apply {
                            setOnImageClickListener {backPosterUrl ->
                                Toast.makeText(activity, "upcoming movie-backPosterUrl: $backPosterUrl", Toast.LENGTH_SHORT)
                                    .show()

//                                navController.navigate(
//                                binding.root.findNavController().navigate(
                                findNavController().navigate(
                                    UpComingMoviesFragmentDirections.actionUpComingMoviesFragmentToDetailsMovieFragment()

                                // Error for navController not set
                                    // java.lang.IllegalStateException: View androidx.constraintlayout.widget.ConstraintLayout does not have a NavController set
                                )

                                // Navigation.findNavController(requireActivity(), R.id.fragment_container_view);
                            }
                        }
                        Log.d("mLogs", "upcoming movies pages: ${moviesReceived.totalPages}")
                    }
                }



                is UIState.ErrorState -> {
                    Toast.makeText(
                        activity,
                        "Error: ${uIState.error}", Toast.LENGTH_LONG)
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