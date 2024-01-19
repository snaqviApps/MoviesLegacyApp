package org.edu.movieslegacyapp.movieslist.presentation.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository


class MoviesListViewModelFactory(private val moviesListRepository: MoviesListRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoviesListViewModel(moviesListRepository) as T
        }
    }


