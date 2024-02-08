package learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import learn.edu.movieslegacyapp.movieslist.data.repository.IMoviesListRepository
import learn.edu.movieslegacyapp.movieslist.util.UIState
import javax.inject.Inject

class MoviesListViewModel @Inject constructor (
    private val iMoviesListRepository: IMoviesListRepository,
    categoryIsPopular: Boolean
) : ViewModel() {

    private val _loadingCheck = MutableLiveData<Boolean>()
    val loadingCheck : LiveData<Boolean> = _loadingCheck

    private val _moviesLoadError = MutableLiveData<Boolean>()
    val moviesLoadError : LiveData<Boolean> = _moviesLoadError

    private val _moviesUIState = MutableLiveData<UIState>()
    val moviesUIState: LiveData<UIState> = _moviesUIState

    init {
        prepareMoviesData(categoryIsPopular)
    }

    private fun prepareMoviesData(categoryIsPopular: Boolean) {
        Log.d("in-viewModel", "calling prepareMoviesData")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("in-viewModel", " update-LiveData-UIState in ${this.javaClass}")
                try {
                    val moviesInfo = iMoviesListRepository.getMoviesList(category = categoryIsPopular, 1)
                    if (moviesInfo?.results?.isNotEmpty() == true) {
                        Log.d("in-repo: ", "${moviesInfo.totalPages}")
                        _moviesUIState.postValue(UIState.SuccessState(movieListDTO = moviesInfo))
                    } else {
                        _moviesUIState.postValue(UIState.ErrorState("data retrieval error"))
                    }
                }
                catch (exception: Exception) {
                        exception.message.let {
                       _moviesUIState.postValue(it?.let { exceptionMessage ->
                                UIState.ErrorState(exceptionMessage)
                            })
                        }
                        UIState.ErrorState("").displayError("in-exceptionMsg: ${exception.message}")
                    }
                }
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