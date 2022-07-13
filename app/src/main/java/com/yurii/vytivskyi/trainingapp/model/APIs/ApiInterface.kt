package com.yurii.vytivskyi.trainingapp.model.APIs

import com.yurii.vytivskyi.trainingapp.data.MovieDetails
import com.yurii.vytivskyi.trainingapp.data.Movies
import com.yurii.vytivskyi.trainingapp.data.ResultX
import com.yurii.vytivskyi.trainingapp.data.Trailers
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET ("3/movie/popular")
    suspend fun getMovies(@Query("api_key") sort : String) : Response<Movies>

    @GET ("3/movie/{movie_id}")
    suspend fun getDetails (@Path ("movie_id") movieIs : Int, @Query("api_key") sort: String,
                    @Query ("language") lang: String ) : Response<MovieDetails>

    @GET ("3/movie/{movie_id}/videos")
    suspend fun getTrailer(@Path ("movie_id") movieID : Int, @Query ("api_key") sort: String,
                   @Query ("language") lang: String) : Response<Trailers>

    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

    }

}

