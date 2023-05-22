package com.cpe

import com.cpe.data.repository.FirebaseRepository
import com.cpe.data.repository.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseRepository(firebaseAuth: FirebaseAuth, db: FirebaseFirestore) : FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseAuth, db)
    }
}