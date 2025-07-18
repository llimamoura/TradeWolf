package com.example.tradewolfapp.viewModel


import android.content.Context
import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class LoginWithGoogleViewModel (private val authRepository: AuthFirebaseRepository) : ViewModel(){

    private val _loginState = MutableStateFlow<LoginResult>(LoginResult.Idle)
     val loginState: StateFlow<LoginResult> = _loginState
    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?> = _user

    init {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            _user.value = currentUser
        }
    }

    fun loginWithGoogle(context: Context) {
        viewModelScope.launch {
            _loginState.value = LoginResult.Loading
            try {
                val credential = authRepository.requestGoogleCredential(context)
                if (credential != null) {
                    authRepository.handleGoogleCredential(credential).fold(
                        onSuccess = { user ->
                            _user.value = user
                            _loginState.value = LoginResult.Success
                        },
                        onFailure = { e ->
                            _loginState.value = LoginResult.Failure(e.message ?: "Unknown error")
                        }
                    )
                } else {
                    _loginState.value = LoginResult.Failure("Google redirect not found")
                }
            } catch (e: Exception) {
                _loginState.value = LoginResult.Failure(e.message ?: "Unknown error")
            }
        }
    }

sealed class LoginResult {
    object Idle : LoginResult()
    object Loading : LoginResult()
    object Success : LoginResult()
    data class Failure(val message: String) : LoginResult()
}
}