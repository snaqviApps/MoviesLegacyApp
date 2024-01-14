package org.edu.movieslegacyapp.di

import androidx.lifecycle.LiveData
import org.edu.movieslegacyapp.api.MoviesApi
import org.edu.movieslegacyapp.data.MovieListDTO
import javax.inject.Inject

class MoviesService {

    @Inject
    lateinit var api : MoviesApi
    init {
        DaggerMoviesComponent.create().inject(this)
    }

    fun getMovies() : LiveData<MovieListDTO> {
        return api.getMovieList()
    }


    /**
     * Error 1:
     * No value passed for parameter 'category'
     * No value passed for parameter 'page'
     *
     * Error: 2
     * Suspend function 'getMovieList' should be called only from a coroutine or
     * another suspend function b
     */

}
