package learn.edu.movieslegacyapp.di

import dagger.Component
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import learn.edu.movieslegacyapp.movieslist.presentation.view.screen.PopularMoviesFragment

@Component(modules = [MovieModule::class])
interface MoviesComponent {

    /**
     * Destinations below are 03 as:
     *  1. MoviesService
     *  2. MoviesListRepository
     *  3. PopularMoviesFragment
     *  (this last injection is violating though the DI-Utilization purpose as it is coupling view with Model, it should be injected
     *  in ViewModel)
     *
     */
    fun inject(moviesService : MoviesService)
    fun inject(moviesListRepository: MoviesListRepository)
    fun inject(popularMoviesFragment: PopularMoviesFragment)

}