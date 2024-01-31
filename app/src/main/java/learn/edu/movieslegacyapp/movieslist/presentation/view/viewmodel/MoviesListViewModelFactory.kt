package learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.edu.movieslegacyapp.di.DaggerMoviesComponent
import learn.edu.movieslegacyapp.movieslist.presentation.IMoviesListRepository
import javax.inject.Inject

class MoviesListViewModelFactory(
    private val categoryIsPopular: Boolean
) : ViewModelProvider.Factory {

    @Inject
    lateinit var iMoviesListRepository: IMoviesListRepository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        DaggerMoviesComponent.create().inject(this)
        return MoviesListViewModel(iMoviesListRepository, categoryIsPopular) as T
    }
}


