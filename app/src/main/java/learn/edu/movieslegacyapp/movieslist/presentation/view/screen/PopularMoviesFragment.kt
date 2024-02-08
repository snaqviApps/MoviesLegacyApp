package learn.edu.movieslegacyapp.movieslist.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import learn.edu.movieslegacyapp.R
import learn.edu.movieslegacyapp.databinding.FragmentPopularMoviesBinding
import learn.edu.movieslegacyapp.movieslist.presentation.view.adapter.MovieRecyclerViewAdapter
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory
import learn.edu.movieslegacyapp.movieslist.util.UIState


/**
 * Handles Popular Movie data (only Poster-view)
 */
class PopularMoviesFragment(
//    private val movieRecyclerViewAdapter: MovieRecyclerViewAdapter
) : Fragment(R.layout.fragment_popular_movies) {

    private lateinit var navHostFragment: NavHostFragment

    private var navHostFrag: NavController? = null
    private var navController: NavController? = null

    private lateinit var moviesRecyclerViewAdapter: MovieRecyclerViewAdapter
    private var fragmentPopularMoviesBinding: FragmentPopularMoviesBinding? = null

    private lateinit var moviesListViewModel: MoviesListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesListViewModelFactory = MoviesListViewModelFactory(true)
        moviesListViewModel = ViewModelProvider(this, moviesListViewModelFactory)[MoviesListViewModel::class.java]

        navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        if(navHostFragment.equals(this)) {
            navController = navHostFragment.navController
        } else {
            navController = null
        }


//        navHostFrag = NavHostFragment.findNavController(this)

        val binding = FragmentPopularMoviesBinding.bind(view)
        fragmentPopularMoviesBinding = binding
        setupObserver(moviesListViewModel, binding)
    }

    private fun setupObserver(
        moviesListViewModel: MoviesListViewModel,
        binding: FragmentPopularMoviesBinding
    ) {
//        moviesListViewModel.moviesUIState.observe(viewLifecycleOwner, Observer { uIState ->
        moviesListViewModel.moviesUIState.observe(viewLifecycleOwner) { uIState ->
            when (uIState) {
                is UIState.EmptyState -> {}
                is UIState.SuccessState -> {
                    val movies = uIState.movieListDTO
                    val sortedMoviesList = movies?.results?.sortedWith(compareBy { it.popularity })       // sorted per popularity

                    // passing data to MovieAdapter
                    moviesRecyclerViewAdapter = MovieRecyclerViewAdapter(sortedMoviesList)
                    binding.rViewPopularMovies.adapter = moviesRecyclerViewAdapter
                    binding.rViewPopularMovies.layoutManager = GridLayoutManager(requireContext(), 2)

                    moviesRecyclerViewAdapter.apply {
                           setOnImageClickListener {backPosterUrl ->
                                Toast.makeText(activity, "popular movie-backPosterUrl: $backPosterUrl", Toast.LENGTH_SHORT).show()

//                               if (PopularMoviesFragment::class.java)
//                                findNavController().navigate(
                                navController?.navigate(
//                               navHostFrag.navigate(
                                    PopularMoviesFragmentDirections.actionPopularMoviesFragmentToDetailsMovieFragment()
                                )
                            }
                    }

                    Log.d("mLogs", "movies pages: ${movies?.totalPages}")

//                    navHostFrag.navigate()
//                    findNavController().navigateUp()
                }

                is UIState.ErrorState -> {
                    Toast.makeText(activity, "Error: ${uIState.error}", Toast.LENGTH_LONG).show()
                    Log.d("mLogs", "Error: ${uIState.error}")
                }
            }
//         })
         }

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentPopularMoviesBinding = null
    }

}