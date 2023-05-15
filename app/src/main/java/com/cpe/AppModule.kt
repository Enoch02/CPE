package com.cpe

import com.cpe.data.repository.FirebaseRepository
import com.cpe.data.repository.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
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
    fun provideFirebaseRepository(firebaseAuth: FirebaseAuth) : FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseAuth)
    }
}