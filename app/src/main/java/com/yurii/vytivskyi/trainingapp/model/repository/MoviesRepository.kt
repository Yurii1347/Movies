package com.yurii.vytivskyi.trainingapp.model.repository


import com.yurii.vytivskyi.trainingapp.data.MovieDetails
import com.yurii.vytivskyi.trainingapp.data.Movies
import com.yurii.vytivskyi.trainingapp.data.Trailers
import retrofit2.Call
import retrofit2.Response

interface MoviesRepository {

   suspend fun getMovies(): Response<Movies>

   suspend fun getDetails(id: Int): Response<MovieDetails>

   suspend fun getTrailer(id: Int, language: String): Response<Trailers>
}