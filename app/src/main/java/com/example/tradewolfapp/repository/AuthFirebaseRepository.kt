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
            Log.e("AuthFirebaseRepository", "Erro ao obter credencial: ${e.localizedMessage}")
            null
        }
    }

    suspend fun handleGoogleCredential(credential: Credential?) {
        if (credential is CustomCredential &&
            credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w("AuthFirebaseRepository", "Google credential not found")
        }
    }

    private suspend fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        try {
            val result = auth.signInWithCredential(credential).await()  // Usa extension da KTX
            val user = result.user
            Log.d("AuthFirebaseRepository", "signInWithCredential: sucess. User: ${user?.uid}")
        } catch (e: Exception) {
            Log.e("AuthFirebaseRepository", "signInWithCredential: error", e)
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