package com.yurii.vytivskyi.trainingapp.model.repository

import com.yurii.vytivskyi.trainingapp.data.User

interface FirebaseRepository {
    fun updateDatabase (firebaseUser: User, uid: String)
}