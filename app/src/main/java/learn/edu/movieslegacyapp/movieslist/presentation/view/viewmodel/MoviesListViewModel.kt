package learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import javax.inject.Inject

class MoviesListViewModel @Inject constructor (
    private val moviesListRepository: MoviesListRepository,
    categoryIsPopular: Boolean
) : ViewModel() {

    val moviesState: LiveData<MoviesListRepository.UIState>
    val moviesLoadError: LiveData<Boolean>
    val loadingCheck: LiveData<Boolean>

    init {
        prepareMoviesData(categoryIsPopular)

        moviesState = moviesListRepository.moviesUIState
        moviesLoadError = moviesListRepository.moviesLoadError
        loadingCheck = moviesListRepository.loadingCheck
    }

    private fun prepareMoviesData(categoryDefault: Boolean) {
        Log.d("in-viewModel", "calling prepareMoviesData")
        viewModelScope.launch {
            moviesListRepository.networkCall(categoryDefault)
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