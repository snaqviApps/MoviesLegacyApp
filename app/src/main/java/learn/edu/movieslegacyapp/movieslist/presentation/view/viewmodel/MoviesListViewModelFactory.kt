package learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import javax.inject.Inject


//class MoviesListViewModelFactory(private val moviesListRepository: MoviesListRepository) : ViewModelProvider.Factory {
class MoviesListViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return MoviesListViewModel(moviesListRepository) as T
            return MoviesListViewModel() as T
        }
    }


