package org.edu.movieslegacyapp.api

import androidx.lifecycle.LiveData
import com.example.movieslegacyapp.BuildConfig.API_KEY
import org.edu.movieslegacyapp.data.MovieListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//abstract class MoviesApi {
interface MoviesApi {
    @GET("movie/{category}")
    suspend fun getMovieList (
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apikey: String = API_KEY
//    ) : MovieListDTO
//    ) : Response<MovieListDTO>
    ) : LiveData<MovieListDTO>

    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}