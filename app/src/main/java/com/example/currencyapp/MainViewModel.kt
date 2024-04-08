package com.example.currencyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyapp.domain.Model.Currency
import com.example.currencyapp.domain.event.CurrencyResult
import com.example.currencyapp.domain.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    private val _currencyData: MutableStateFlow<Currency?> = MutableStateFlow(null)
    val currencyData = _currencyData.asStateFlow()


    private val _showToast = Channel<Boolean>()
    val showToast = _showToast.receiveAsFlow()

    init {
        viewModelScope.launch {
            while (isActive) {
                currencyRepository.getLiveCurrency().collectLatest { currencyResult ->
                    when (currencyResult) {
                        is CurrencyResult.Failure -> {
                            _showToast.send(true)
                            delay(5000)
                        }
                        is CurrencyResult.Success -> {
                            currencyResult.data?.let { currency ->
                                _currencyData.update {
                                    currency
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}