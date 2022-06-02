package com.yurii.vytivskyi.trainingapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yurii.vytivskyi.trainingapp.R
import com.yurii.vytivskyi.trainingapp.databinding.ActivityMoviesBinding
import com.yurii.vytivskyi.trainingapp.view.adaptors.SimpleAdapter
import com.yurii.vytivskyi.trainingapp.viewmodel.MoviesVM

class MoviesActivity: AppCompatActivity(), SimpleAdapter.ItemClickListener {
    private var backPressedTime:Long = 0

    private val mViewModel: MoviesVM = MoviesVM()


    private lateinit var mMoviesAdapter: SimpleAdapter
    private lateinit var mRecyclerView: RecyclerView

    lateinit var binding : ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservers()
        mViewModel.getMovies()
    }
    private fun initObservers() {
        mViewModel.apply {
            movies.observe(this@MoviesActivity){
                mMoviesAdapter = SimpleAdapter(it, this@MoviesActivity)
                mRecyclerView.adapter = mMoviesAdapter
            }
        }
    }

    private fun initViews() {
        mRecyclerView = binding.recyclerView
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onClick(id: Int) {
        val i = Intent(this@MoviesActivity, MovieDetailsActivity::class.java)
        i.putExtra("id", id)
        startActivity(i)
    }



    override fun onBackPressed() {
       val backToast = Toast.makeText(this, resources.getString(R.string.movies_activity_exit_message), Toast.LENGTH_LONG)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.finishAffinity()
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}