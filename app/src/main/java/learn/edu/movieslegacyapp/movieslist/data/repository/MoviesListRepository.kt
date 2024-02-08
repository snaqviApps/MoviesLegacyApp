package learn.edu.movieslegacyapp.movieslist.data.repository

import learn.edu.movieslegacyapp.di.DaggerMoviesComponent
import learn.edu.movieslegacyapp.di.MoviesService
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesListRepository @Inject constructor(
    private val moviesService: MoviesService

) : IMoviesListRepository {

    init {
        DaggerMoviesComponent.create().inject(this)
    }

    override suspend fun getMoviesList(
        category: Boolean,
        page: Int
    ): MovieListDTO? = moviesService.getMovies(category, page)

}