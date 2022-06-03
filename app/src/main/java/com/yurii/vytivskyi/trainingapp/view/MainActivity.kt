package com.yurii.vytivskyi.trainingapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yurii.vytivskyi.trainingapp.R
import com.yurii.vytivskyi.trainingapp.data.User
import com.yurii.vytivskyi.trainingapp.databinding.ActivityMainBinding
import com.yurii.vytivskyi.trainingapp.viewmodel.MainVM

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val mMainViewModel: MainVM = MainVM()
    private val signInLauncher = registerForActivityResult(FirebaseAuthUIActivityResultContract()) {
        res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openRegistrationScreen()

        val user = Firebase.auth.currentUser
        if (user != null) {
            val i = Intent (this, MoviesActivity::class.java)
            startActivity(i)
        } else {
            openRegistrationScreen()
        }


    }

    private fun openRegistrationScreen(){
        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.logo)
            .setTheme(R.style.Theme_TrainingApp)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult){

        if (result.resultCode == RESULT_OK) {
            val authUser = FirebaseAuth.getInstance().currentUser
            authUser?.let {
                val user = User(authUser.email.toString(), authUser.uid)
                 mMainViewModel.updateDatabase(user, user.uId)
                 }
            binding.activityMainIvStart.setOnClickListener{
                val i = Intent (this, MoviesActivity::class.java)
                startActivity(i)
            }
            val i = Intent (this, MoviesActivity::class.java)
            startActivity(i)
        }
        else {

            binding.btStart.visibility = View.VISIBLE
            binding.btStart.setOnClickListener {

                openRegistrationScreen()
            }
        }
    }



}