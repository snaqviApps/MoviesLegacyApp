package learn.edu.movieslegacyapp.di

import dagger.Component
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository

@Component(modules = [MovieModule::class])
interface MoviesComponent {

    fun inject(moviesService : MoviesService)
    fun inject(moviesListRepository: MoviesListRepository)

}