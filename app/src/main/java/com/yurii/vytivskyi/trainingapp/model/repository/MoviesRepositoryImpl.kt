package com.yurii.vytivskyi.trainingapp.model.repository

import com.yurii.vytivskyi.trainingapp.Constants
import com.yurii.vytivskyi.trainingapp.data.MovieDetails
import com.yurii.vytivskyi.trainingapp.data.Movies
import com.yurii.vytivskyi.trainingapp.data.Trailers
import com.yurii.vytivskyi.trainingapp.model.APIs.ApiInterface
import retrofit2.Call
import retrofit2.Response

class MoviesRepositoryImpl: MoviesRepository {
    private val apiInterface = ApiInterface.create()

    override suspend fun getMovies(): Response<Movies> {
       return apiInterface.getMovies(Constants.api_key)
    }

    override suspend fun getDetails(id: Int): Response<MovieDetails> {
        return apiInterface.getDetails(id, Constants.api_key, "ru")
    }

    override suspend fun getTrailer(id: Int, language: String): Response<Trailers> {
        return apiInterface.getTrailer(id, Constants.api_key, language)
    }


}