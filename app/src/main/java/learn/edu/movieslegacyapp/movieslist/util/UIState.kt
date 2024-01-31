package learn.edu.movieslegacyapp.movieslist.util

import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO

sealed class UIState {
    data object EmptyState : UIState()
    class SuccessState(val movieListDTO: MovieListDTO?) : UIState()
    class ErrorState(val error: String) : UIState() {
        fun displayError(error: String) {
            println("This is error: $error")
        }
    }
}