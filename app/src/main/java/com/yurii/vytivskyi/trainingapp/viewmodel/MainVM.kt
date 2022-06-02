package com.yurii.vytivskyi.trainingapp.viewmodel


import com.yurii.vytivskyi.trainingapp.data.User
import com.yurii.vytivskyi.trainingapp.model.repository.FirebaseRepository
import com.yurii.vytivskyi.trainingapp.model.repository.FirebaseRepositoryImpl

class MainVM {
    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateDatabase(firebaseUser: User, uid: String){
        mFirebaseRepository.updateDatabase(firebaseUser, uid)
    }
}