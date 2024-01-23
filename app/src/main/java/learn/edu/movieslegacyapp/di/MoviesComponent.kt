package learn.edu.movieslegacyapp.di

import dagger.Component
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModel
import learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel.MoviesListViewModelFactory

/**
 * This class contains the 'injection' mechanism for the destination below, as:
 *  There are Three possible-destinations, that could receive the dependencies available in MovieModule-class
 *  using the method-call MoviesComponent.create().inject()
 *
 *  1. MoviesService
 *  2. MoviesListRepository
 *  3. MoviesListViewModel
 *
 */

@Component(modules = [MovieModule::class])
interface MoviesComponent {

    fun inject(moviesService : MoviesService)
    fun inject(moviesListRepository: MoviesListRepository)
    fun inject(moviesListViewModel: MoviesListViewModel)
    fun inject(viewModelFactory: MoviesListViewModelFactory)

}