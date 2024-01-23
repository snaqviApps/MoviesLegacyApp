package learn.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import learn.edu.movieslegacyapp.di.DaggerMoviesComponent
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
import javax.inject.Inject

class MoviesListViewModelFactory(
    private val categoryIsPopular: Boolean
) : ViewModelProvider.Factory {

    @Inject
    lateinit var moviesListRepository: MoviesListRepository

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        DaggerMoviesComponent.create().inject(this)
//            return MoviesListViewModel(categoryIsPopular) as T
            return MoviesListViewModel(moviesListRepository, categoryIsPopular) as T
        }
    }


