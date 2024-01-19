package org.edu.movieslegacyapp.movieslist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.edu.movieslegacyapp.di.DaggerMoviesComponent
import org.edu.movieslegacyapp.di.MoviesService
import org.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import org.edu.movieslegacyapp.movieslist.util.CoreConstants.Companion.MAX_TIME_OUT
import javax.inject.Inject

class MoviesListRepository {

    @Inject
    lateinit var moviesService: MoviesService

    private val _moviesUIState = MutableLiveData<UIState>()
    val moviesUIState: LiveData<UIState> = _moviesUIState
    val loadingCheck = MutableLiveData<Boolean>()
    val moviesLoadError = MutableLiveData<Boolean>()

    init {
        DaggerMoviesComponent.create().inject(this)
    }

    suspend fun networkCall() {
            withContext(Dispatchers.IO) {
                withTimeout(MAX_TIME_OUT) {
                    try {
                        Log.d("in-repo", " networkCall in try-block")
                        val moviesInfo = moviesService.getMovies()
                        if (moviesInfo?.results?.isNotEmpty() == true) {
                            Log.d("in-repo: ", "${moviesInfo.totalPages}")
                            _moviesUIState.postValue(UIState.SuccessState(movieListDTO = moviesInfo))
                        } else {
                            _moviesUIState.postValue(UIState.ErrorState("data retrieval error"))
                        }
                    } catch (exception: Exception) {
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
    sealed class UIState {
        data object EmptyState : UIState()
        class SuccessState(val movieListDTO: MovieListDTO?) : UIState()
        class ErrorState(val error: String) : UIState() {
            fun displayError(error: String) {
                println("This is error: $error")
            }
        }
    }

}

