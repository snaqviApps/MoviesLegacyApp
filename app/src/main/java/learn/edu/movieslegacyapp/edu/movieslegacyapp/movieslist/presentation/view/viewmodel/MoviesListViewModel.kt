package learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository

class MoviesListViewModel(private val repo : MoviesListRepository) : ViewModel() {

    val moviesState = repo.moviesUIState
    val moviesLoadError = repo.moviesLoadError
    val loadingCheck = repo.loadingCheck

    init {
        prepareMoviesData()
    }

    private fun prepareMoviesData() {
        Log.d("in-viewModel", "calling prepareMoviesData")
        viewModelScope.launch {
            repo.networkCall()
        }
    }

}

/**
 * to implement MVI Architecture
 * name branch: toMVI_arch
 * sample: https://medium.com/@myofficework000/mvvm-to-mvi-a-guide-to-migrating-your-android-architecture-8d3cb5bb9f06
 */
sealed class MyIntent {
    object LoadData : MyIntent()
    data class UpdateData(val movieListDTO: MovieListDTO) : MyIntent()
    object DeleteData : MyIntent()
}