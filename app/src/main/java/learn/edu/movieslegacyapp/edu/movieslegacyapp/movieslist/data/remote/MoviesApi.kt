package learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.data.remote

import learn.edu.movieslegacyapp.BuildConfig.API_KEY
import learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.data.remote.response.MovieListDTO
import learn.edu.movieslegacyapp.edu.movieslegacyapp.movieslist.util.Category
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//abstract class MoviesApi {
interface MoviesApi {
    @GET("movie/{category}")
    suspend fun getMovieList (
        @Path("category") category: String = Category.POPULAR,      // default
        @Query("page") page: Int = 1,                               // default
        @Query("api_key") apikey: String = API_KEY
    ) : MovieListDTO?
//    ) : Response<MovieListDTO>
//    ) : LiveData<MovieListDTO>



    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}