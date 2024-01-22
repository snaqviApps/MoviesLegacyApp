package learn.edu.movieslegacyapp.di

import learn.edu.movieslegacyapp.movieslist.data.remote.MoviesApi
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import learn.edu.movieslegacyapp.movieslist.util.Category
import javax.inject.Inject

class MoviesService {

    @Inject
    lateinit var api : MoviesApi            // field Injection, to be utilized for providing Retrofit response at line# 17

    init {
      DaggerMoviesComponent.create().inject(this)
    }

    suspend fun getMovies(movieCategoryIsPopular: Boolean): MovieListDTO? {
        return when(movieCategoryIsPopular) {
            true -> api.getMovieList()
            false -> api.getMovieList(Category.UPCOMING)
        }
    }

}
