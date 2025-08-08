package com.example.tradewolfapp.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tradewolfapp.repository.AuthFirebaseRepository

class LoginWithGoogleViewModelFactory (
    private val authRepository: AuthFirebaseRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginWithGoogleViewModel(authRepository) as T


    }
}