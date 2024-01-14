package org.edu.movieslegacyapp.di

import dagger.Component

@Component(modules = [MovieModule::class])
interface MoviesComponent {

    fun inject(moviesService : MoviesService)

}