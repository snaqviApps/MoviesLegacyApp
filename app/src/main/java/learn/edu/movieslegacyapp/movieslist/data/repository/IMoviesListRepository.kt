package learn.edu.movieslegacyapp.movieslist.data.repository

import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO

interface IMoviesListRepository {
    suspend fun getMoviesList (
        category : Boolean,
        page : Int
    ) : MovieListDTO?
}
