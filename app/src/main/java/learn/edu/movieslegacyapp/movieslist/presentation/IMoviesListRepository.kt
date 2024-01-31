package learn.edu.movieslegacyapp.movieslist.presentation

import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO

interface IMoviesListRepository {
    suspend fun getMoviesList (
        category : Boolean,
        page : Int
    ) : MovieListDTO?
}
