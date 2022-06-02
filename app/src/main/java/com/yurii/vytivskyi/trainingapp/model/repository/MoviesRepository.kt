package com.yurii.vytivskyi.trainingapp.model.repository

import com.yurii.vytivskyi.trainingapp.data.MovieDetails
import com.yurii.vytivskyi.trainingapp.data.Movies
import com.yurii.vytivskyi.trainingapp.data.ResultX
import com.yurii.vytivskyi.trainingapp.data.Trailers


import retrofit2.Call

interface MoviesRepository {

    fun getMovies(): Call<Movies>

    fun getDetails(id: Int): Call<MovieDetails>

    fun getTrailer(id: Int, language: String): Call<Trailers>
}