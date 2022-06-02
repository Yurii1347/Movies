package com.yurii.vytivskyi.trainingapp.model.repository

import com.yurii.vytivskyi.trainingapp.Constants
import com.yurii.vytivskyi.trainingapp.data.MovieDetails
import com.yurii.vytivskyi.trainingapp.data.Movies
import com.yurii.vytivskyi.trainingapp.data.Trailers
import com.yurii.vytivskyi.trainingapp.model.APIs.ApiInterface
import retrofit2.Call

class MoviesRepositoryImpl: MoviesRepository {
    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
       return apiInterface.getMovies(Constants.api_key)
    }

    override fun getDetails(id: Int): Call<MovieDetails> {
        return apiInterface.getDetails(id, Constants.api_key, "ru")
    }

    override fun getTrailer(id: Int, language: String): Call<Trailers> {
        return apiInterface.getTrailer(id, Constants.api_key, language)
    }


}