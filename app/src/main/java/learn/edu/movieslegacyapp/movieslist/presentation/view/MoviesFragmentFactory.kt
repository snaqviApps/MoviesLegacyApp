package learn.edu.movieslegacyapp.movieslist.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import learn.edu.movieslegacyapp.movieslist.presentation.view.screen.PopularMoviesFragment
import learn.edu.movieslegacyapp.movieslist.presentation.view.screen.UpComingMoviesFragment
import javax.inject.Inject

class MoviesFragmentFactory @Inject constructor(
//    private val movieRecyclerViewAdapter: MovieRecyclerViewAdapter,

) : FragmentFactory() {


    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){

            PopularMoviesFragment::class.java.name -> PopularMoviesFragment(
//                movieRecyclerViewAdapter = movieRecyclerViewAdapter,
            )

            UpComingMoviesFragment::class.java.name -> UpComingMoviesFragment<String>()

            else -> super.instantiate(classLoader, className)
        }
    }
}