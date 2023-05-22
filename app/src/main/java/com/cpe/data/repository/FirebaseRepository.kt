package com.cpe.data.repository

import com.cpe.data.models.News
import com.cpe.data.models.TimeTable
import com.cpe.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    fun isUserLoggedIn(): Boolean
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>

    suspend fun getData() : List<TimeTable>

    suspend fun getNews() : List<News>
}
