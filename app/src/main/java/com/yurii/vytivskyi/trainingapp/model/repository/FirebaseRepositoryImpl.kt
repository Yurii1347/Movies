package com.yurii.vytivskyi.trainingapp.model.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yurii.vytivskyi.trainingapp.data.User

class FirebaseRepositoryImpl: FirebaseRepository {
    private val database: DatabaseReference = Firebase.database.reference
    override fun updateDatabase(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser)
    }
}