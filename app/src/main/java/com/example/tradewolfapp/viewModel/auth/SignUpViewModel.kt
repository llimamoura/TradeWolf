package com.example.tradewolfapp.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradewolfapp.model.AuthModel
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel (private val authRepository: AuthFirebaseRepository) : ViewModel() {
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun signUp(authModel: AuthModel) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            try {
                val result = authRepository.singUp(authModel)
                result.fold(
                    onSuccess = { user ->
                        _signUpState.value = SignUpState.Success(user)
                    },
                    onFailure = { e ->
                        _signUpState.value = SignUpState.Error(e.message ?: "Unknown error")
                    }
                )

            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class SignUpState{
    object Idle : SignUpState()
    object Loading : SignUpState()
    data class Success(val user: FirebaseUser) : SignUpState()
    data class Error(val message: String) : SignUpState()

}