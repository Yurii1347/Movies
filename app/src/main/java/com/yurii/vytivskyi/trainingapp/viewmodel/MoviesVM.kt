package com.yurii.vytivskyi.trainingapp.viewmodel


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


    suspend fun getMovies() {
        val result = mMoviesRepository.getMovies()
        result.isSuccessful
        _movies.postValue(result.body()?.results)
    }

    suspend fun getDetails(id: Int) {
        val result = mMoviesRepository.getDetails(id)
        result.isSuccessful
        _movieDetails.postValue(result.body())
    }

    suspend fun getTrailerRU(id: Int) {
        val result = mMoviesRepository.getTrailer(id, "ru")
        result.isSuccessful
        _trailerRU.postValue(result.body()?.results)
    }

    suspend fun getTrailerEN(id: Int) {
        val result = mMoviesRepository.getTrailer(id, "en")
        result.isSuccessful
        _trailerEN.postValue(result.body()?.results)

    }

}