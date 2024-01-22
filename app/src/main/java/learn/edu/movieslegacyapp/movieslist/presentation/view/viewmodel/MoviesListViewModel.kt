package learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import javax.inject.Inject


class MoviesListViewModel @Inject constructor (
    private val moviesListRepository : MoviesListRepository,
    categoryDefault : Boolean) : ViewModel() {
//class MoviesListViewModel : ViewModel() {

//    @Inject
//    lateinit var moviesListRepository : MoviesListRepository

    val moviesState = moviesListRepository.moviesUIState
    val moviesLoadError = moviesListRepository.moviesLoadError
    val loadingCheck = moviesListRepository.loadingCheck

    init {
        prepareMoviesData(categoryDefault)
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