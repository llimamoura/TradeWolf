package com.example.tradewolfapp.repository

import com.example.tradewolfapp.model.AuthModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthFirebaseRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun login(authModel: AuthModel): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(authModel.email, authModel.password).await()
            val user = result.user ?: throw Exception("User not found")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun singUp(authModel: AuthModel): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(authModel.email, authModel.password).await()
            val user = result.user ?: throw Exception("Unknown error")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}