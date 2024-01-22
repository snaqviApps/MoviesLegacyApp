package learn.edu.movieslegacyapp.di


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import learn.edu.movieslegacyapp.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import learn.edu.movieslegacyapp.movieslist.data.remote.MoviesApi
import learn.edu.movieslegacyapp.movieslist.presentation.MoviesListRepository
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
    fun providesMoviesApi() : MoviesApi {
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

    @Provides
//    fun providesMoviesListRepository(moviesService: MoviesService) : MoviesListRepository {
    fun providesMoviesListRepository() : MoviesListRepository {
        return MoviesListRepository()
    }

}