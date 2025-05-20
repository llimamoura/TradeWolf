package com.example.tradewolfapp.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class LoginWithGoogleViewModel (private val authRepository: AuthFirebaseRepository) : ViewModel(

){


    private val _loginState = MutableStateFlow<LoginResult>(LoginResult.Idle)
     val loginState: StateFlow<LoginResult> = _loginState

    fun loginWithGoogle(context: Context) {
        viewModelScope.launch {
            try {
                val credential = authRepository.requestGoogleCredential(context)
                if (credential != null) {
                    authRepository.handleGoogleCredential(credential)
                    _loginState.value = LoginResult.Success
                } else {
                    _loginState.value = LoginResult.Failure("Google credential not found")
                }
            } catch (e: Exception) {
                _loginState.value = LoginResult.Failure(e.message ?: "Unknown error")
            }
        }
    }

}

sealed class LoginResult {
    object Idle : LoginResult()
    object Loading : LoginResult()
    object Success : LoginResult()
    data class Failure(val message: String) : LoginResult()
}