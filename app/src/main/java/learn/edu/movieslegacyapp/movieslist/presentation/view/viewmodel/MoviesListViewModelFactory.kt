package learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository


class MoviesListViewModelFactory(
    private val repository: MoviesListRepository,
    private val categoryIsPopular: Boolean
) : ViewModelProvider.Factory {
//class MoviesListViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoviesListViewModel(repository, categoryIsPopular) as T
//            return MoviesListViewModel() as T
        }
    }


