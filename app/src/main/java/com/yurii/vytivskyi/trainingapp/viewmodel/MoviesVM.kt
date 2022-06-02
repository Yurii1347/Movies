package com.yurii.vytivskyi.trainingapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yurii.vytivskyi.trainingapp.data.MovieDetails
import com.yurii.vytivskyi.trainingapp.data.Movies
import com.yurii.vytivskyi.trainingapp.data.ResultX
import com.yurii.vytivskyi.trainingapp.data.Trailers
import com.yurii.vytivskyi.trainingapp.model.repository.MoviesRepository
import com.yurii.vytivskyi.trainingapp.model.repository.MoviesRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MoviesVM  {

    private val mMoviesRepository: MoviesRepository = MoviesRepositoryImpl()

    private val _movies = MutableLiveData<List<com.yurii.vytivskyi.trainingapp.data.Result?>>()
    val movies : LiveData<List<com.yurii.vytivskyi.trainingapp.data.Result?>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails> = _movieDetails

    private val _trailerRU = MutableLiveData<List<ResultX>>()
    val trailerRU : LiveData<List<ResultX>> = _trailerRU

    private val _trailerEN = MutableLiveData<List<ResultX>>()
    val trailerEN : LiveData<List<ResultX>> = _trailerEN


    fun getMovies() {
        val result = mMoviesRepository.getMovies()
        result.enqueue( object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                _movies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
            }
        })
    }

     fun getDetails(id: Int) {
        val result = mMoviesRepository.getDetails(id)
         result.enqueue( object : Callback<MovieDetails>{
             override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                 _movieDetails.postValue(response.body())
             }
             override fun onFailure(call: Call<MovieDetails>, t: Throwable) {

             }
         })
    }

    fun getTrailerRU(id: Int) {
        val result = mMoviesRepository.getTrailer(id, "ru")
        result.enqueue( object : Callback<Trailers> {
            override fun onResponse(call: Call<Trailers>, response: Response<Trailers>) {

                _trailerRU.postValue(response.body()?.results)

            }

            override fun onFailure(call: Call<Trailers>, t: Throwable) {

            }

        })
    }
    fun getTrailerEN(id: Int) {
        val result = mMoviesRepository.getTrailer(id, "en")
        result.enqueue( object : Callback<Trailers> {
            override fun onResponse(call: Call<Trailers>, response: Response<Trailers>) {

                _trailerEN.postValue(response.body()?.results)

            }

            override fun onFailure(call: Call<Trailers>, t: Throwable) {

            }

        })
    }
}