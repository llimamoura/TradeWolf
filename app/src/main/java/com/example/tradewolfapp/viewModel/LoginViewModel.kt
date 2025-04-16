package com.example.tradewolfapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradewolfapp.model.AuthModel
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel (private val authRepository: AuthFirebaseRepository) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(authModel: AuthModel) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val result = authRepository.login(authModel)
                result.fold(
                    onSuccess = { user ->
                        _loginState.value = LoginState.Success(user)
                    },
                    onFailure = { e ->
                        _loginState.value = LoginState.Error(e.message ?: "Unknown error")
                    }
                )
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Unknown error")
            }
        }
    }
    fun resetLoginState() {
        _loginState.value = LoginState.Idle
    }
}

sealed class LoginState{
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: FirebaseUser) : LoginState()
    data class Error(val message: String) : LoginState()

}