package org.edu.movieslegacyapp.di

import org.edu.movieslegacyapp.movieslist.data.remote.MoviesApi
import org.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import javax.inject.Inject

class MoviesService {

    @Inject
    lateinit var api : MoviesApi            // field Injection

    init {
      DaggerMoviesComponent.create().inject(this)
    }

    suspend fun getMovies() : MovieListDTO? {
        return api.getMovieList()
    }

}
