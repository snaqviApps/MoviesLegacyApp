//package learn.edu.movieslegacyapp.movieslist.presentation.view.screen
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentFactory
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import learn.edu.movieslegacyapp.movieslist.data.repository.MoviesListRepository
//import javax.inject.Inject
//
//class MoviesFragmentsFactory (
////class MoviesFragmentsFactory @Inject constructor (
//    private val repository: MoviesListRepository
////    private val movieRecyclerViewAdapter : MovieRecyclerViewAdapter,
//
//) : FragmentFactory() {
//
////        @Inject
////        lateinit var repositoryInFragmentsFactory: MoviesListRepository
//        @OptIn(ExperimentalCoroutinesApi::class)
//        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
//            return when(className){
//
////               //  using FragmentFactory does not work
////                PopularMoviesFragment::class.java.name -> PopularMoviesFragment()       // app not crashoing, however data not being passed to adapter
//                PopularMoviesFragment::class.java.name -> PopularMoviesFragment(repository)
//
//
//                UpComingMoviesFragment::class.java.name -> UpComingMoviesFragment()
//                DetailsMovieFragment::class.java.name -> DetailsMovieFragment()
//                else -> super.instantiate(classLoader, className)
//            }
//        }
//
//}