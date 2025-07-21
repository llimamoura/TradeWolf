package com.example.tradewolfapp.repository

import android.content.Context
import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.tradewolfapp.R
import com.example.tradewolfapp.model.AuthModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class AuthFirebaseRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    suspend fun login(authModel: AuthModel): Result<FirebaseUser> {
        return try {
            val result =
                auth.signInWithEmailAndPassword(authModel.email, authModel.password).await()
            val user = result.user ?: throw Exception("User not found")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun requestGoogleCredential(context: Context): Credential? {
        val serverClientId = context.getString(R.string.web_client_id)

        val googleOption = GetGoogleIdOption.Builder()
            .setServerClientId(serverClientId)
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleOption)
            .build()

        val credentialManager = CredentialManager.create(context)

        return try {
            val result = credentialManager.getCredential(context, request)
            result.credential
        } catch (e: Exception) {
            null
        }
    }

    suspend fun handleGoogleCredential(credential: Credential?): Result<FirebaseUser> {
        return try {
            if (credential is CustomCredential &&
                credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
            } else {
                Result.failure(Exception("Invalid Google credential"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun firebaseAuthWithGoogle(idToken: String): Result<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val user = result.user ?: throw Exception("User not found")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun singUp(authModel: AuthModel): Result<FirebaseUser> {
        return try {
            val result =
                auth.createUserWithEmailAndPassword(authModel.email, authModel.password).await()
            val user = result.user ?: throw Exception("Unknown error")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}