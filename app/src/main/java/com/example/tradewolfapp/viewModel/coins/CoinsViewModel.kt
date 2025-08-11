package com.example.tradewolfapp.viewModel.coins

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradewolfapp.model.CoinModel
import com.example.tradewolfapp.repository.CoinsRepository
import com.example.tradewolfapp.repository.ConfigRetrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinsViewModel: ViewModel() {
    private val repository = CoinsRepository(ConfigRetrofit.services)

    private val _coins = MutableStateFlow<List<CoinModel>>(emptyList())

    val coins: StateFlow<List<CoinModel>> = _coins.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    fun loadCoinsIfNeeded() {
        if (_coins.value.isEmpty()) {
            loadCoins()
        } 
    }

 fun loadCoins() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val coinsList = repository.fetchCoins()
                Log.d("API_DEBUG", "Dados recebidos: ${coinsList.size} itens")
                _coins.value = coinsList
                _error.value = null
                _isSuccess.value = true
            } catch (e: Exception) {
                _error.value = "Falha ao carregar dados: ${e.localizedMessage}"
                _coins.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

}