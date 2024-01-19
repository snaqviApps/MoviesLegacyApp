package org.edu.movieslegacyapp.di

import com.example.movieslegacyapp.BuildConfig.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.edu.movieslegacyapp.movieslist.data.remote.MoviesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MovieModule {

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor = interceptor)
        .build()

    @Provides
    fun providesCountriesApi() : MoviesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    fun providesMoviesService() : MoviesService {
        return MoviesService()
    }

}