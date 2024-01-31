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

    suspend fun getMovies(movieCategoryIsPopular: Boolean, page: Int): MovieListDTO? {
        return when(movieCategoryIsPopular) {
            true -> api.getMovieList(Category.POPULAR, page)
            false -> api.getMovieList(Category.UPCOMING, page)
        }
    }

}
