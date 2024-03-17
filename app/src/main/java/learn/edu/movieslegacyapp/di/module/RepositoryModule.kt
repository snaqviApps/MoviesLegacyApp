package learn.edu.movieslegacyapp.di.module

import dagger.Binds
import dagger.Module
import learn.edu.movieslegacyapp.movieslist.data.repository.MoviesListRepository
import learn.edu.movieslegacyapp.movieslist.data.repository.MoviesListRepositoryImpl
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        moviesListRepository: MoviesListRepositoryImpl
    ): MoviesListRepository

}