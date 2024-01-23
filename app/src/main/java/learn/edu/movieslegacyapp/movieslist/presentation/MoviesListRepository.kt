package learn.edu.movieslegacyapp.movieslist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import learn.edu.movieslegacyapp.di.DaggerMoviesComponent
import learn.edu.movieslegacyapp.di.MoviesService
import learn.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import learn.edu.movieslegacyapp.movieslist.util.CoreConstants.Companion.MAX_TIME_OUT
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesListRepository {

    @Inject
    lateinit var moviesService: MoviesService

    private val _moviesUIState = MutableLiveData<UIState>()
    val moviesUIState: LiveData<UIState> = _moviesUIState

    private val _loadingCheck = MutableLiveData<Boolean>()
    val loadingCheck : LiveData<Boolean> = _loadingCheck

    private val _moviesLoadError = MutableLiveData<Boolean>()
    val moviesLoadError : LiveData<Boolean> = _moviesLoadError

    init {
        DaggerMoviesComponent.create().inject(this)
    }

    suspend fun networkCall(movieCategoryIsPopular: Boolean) {
            withContext(Dispatchers.IO) {
                withTimeout(MAX_TIME_OUT) {
                    try {
                        Log.d("in-repo", " networkCall in try-block")
                        val moviesInfo = moviesService.getMovies(movieCategoryIsPopular)
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


