package learn.edu.movieslegacyapp.movieslist.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import learn.edu.movieslegacyapp.movieslist.presentation.view.adapter.MovieRecyclerViewAdapter
import learn.edu.movieslegacyapp.movieslist.presentation.view.screen.PopularMoviesFragment
import javax.inject.Inject

class MoviesFragmentFactory @Inject constructor(
    private val movieRecyclerViewAdapter: MovieRecyclerViewAdapter
) : FragmentFactory() {



//class ArtFragmentFactory (
//    private val artRecyclerAdapter: ArtRecyclerAdapter,
//    private val imageRecyclerAdapter: ImageRecyclerAdapter,
//    private val glide : RequestManager
//) : FragmentFactory() {

//    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
//            PopularMoviesFragment::class.java.name -> PopularMoviesFragment(movieRecyclerViewAdapter = movieRecyclerViewAdapter)

//            ArtsDetailFragment::class.java.name -> ArtsDetailFragment(glide)
//            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}