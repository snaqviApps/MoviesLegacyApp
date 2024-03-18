package learn.edu.movieslegacyapp.movieslist.data.repository

import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO

interface MoviesListRepository {
    suspend fun getMoviesList (
        category : Boolean,
        page : Int
    ) : MovieListDTO?
}
