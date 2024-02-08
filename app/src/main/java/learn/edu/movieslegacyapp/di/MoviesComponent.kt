package learn.edu.movieslegacyapp.di

import dagger.Component
import learn.edu.movieslegacyapp.MainActivity
import learn.edu.movieslegacyapp.di.module.MovieModule
import learn.edu.movieslegacyapp.di.module.RepositoryModule
import learn.edu.movieslegacyapp.movieslist.data.repository.MoviesListRepository
import learn.edu.movieslegacyapp.movieslist.presentation.view.MoviesFragmentFactory
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory
import javax.inject.Singleton

/**
 * This class contains the 'injection' mechanism for the destination below, as:
 *  There are Three possible-destinations, that could receive the dependencies available in
 *  MovieModule-class using the method-call MoviesComponent.create().inject()
 *
 *  1. MoviesService
 *  2. MoviesListRepository
 *  3. MoviesListViewModel
 *
 */

@Singleton
@Component(
    modules = [
        MovieModule::class,
        RepositoryModule::class]
)
interface MoviesComponent {

    fun inject(moviesService: MoviesService)
    fun inject(moviesListRepository: MoviesListRepository)
    fun inject(moviesListViewModel: MoviesListViewModel)
    fun inject(viewModelFactory: MoviesListViewModelFactory)

    fun inject(moviesFragmentFactory: MoviesFragmentFactory)
    fun inject(mainActivity: MainActivity)

}