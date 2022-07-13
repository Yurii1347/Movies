package com.yurii.vytivskyi.trainingapp.view


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Picasso
import com.yurii.vytivskyi.trainingapp.Constants
import com.yurii.vytivskyi.trainingapp.data.MovieDetails
import com.yurii.vytivskyi.trainingapp.data.ResultX
import com.yurii.vytivskyi.trainingapp.databinding.ActivityMoviesDetailsBinding
import com.yurii.vytivskyi.trainingapp.viewmodel.MoviesVM
import kotlinx.coroutines.launch


class MovieDetailsActivity : AppCompatActivity() {

    private val mViewModel: MoviesVM = MoviesVM()

    private lateinit var binding : ActivityMoviesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

      val id =  intent.getIntExtra("id", 0)

        initObservers()
        lifecycleScope.launch {

            mViewModel.getDetails(id)
        }

        binding.moviesDetailsBtPlay.setOnClickListener {
            lifecycleScope.launch {
                mViewModel.getTrailerRU(id)
                mViewModel.getTrailerEN(id)
            }

            initObserveTrailerRU()
            }

    }
    private fun initObservers() {
        mViewModel.apply {
            movieDetails.observe(this@MovieDetailsActivity){
                setMovieInformation(it)
            }
        }
    }

  private fun initObserveTrailerRU() {
             mViewModel.trailerRU.observe(this@MovieDetailsActivity){
                    if (it.isNotEmpty()){
                        startYouTubeTrailer(it[0])
                    } else initObserverTrailerEN()
            }

  }

    private fun setMovieInformation(movieDetails: MovieDetails?) {
        binding.moviesDetailsTitle.text = movieDetails?.title
        binding.moviesDetailsReleas.text = movieDetails?.release_date
        binding.moviesDetailsDescription.text = movieDetails?.overview
        binding.moviesDetailsScore.text = movieDetails?.vote_average.toString()

  Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movieDetails?.backdrop_path).into(binding.moviesDetailsImage)

     }

    private fun startYouTubeTrailer(resultX: ResultX){
        val i = Intent(YouTubeStandalonePlayer
            .createVideoIntent( this@MovieDetailsActivity, Constants.YOUTUBE_API_KEY, resultX.key, 0, true, true))
            startActivity(i)

    }

    private fun initObserverTrailerEN(){
        mViewModel.trailerEN.observe(this@MovieDetailsActivity){
            startYouTubeTrailer(it[0])
        }
    }


 }





