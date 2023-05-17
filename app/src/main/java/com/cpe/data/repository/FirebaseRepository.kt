package com.cpe.data.repository

import com.cpe.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    fun isUserLoggedIn(): Boolean
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>

}
