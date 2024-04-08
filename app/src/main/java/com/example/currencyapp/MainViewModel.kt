package com.example.currencyapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
import kotlin.math.log


@SuppressLint("MissingPermission")
@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val notificationCompatBuilder : NotificationCompat.Builder,
    private val notificationManagerCompat: NotificationManagerCompat
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
                                notificationManagerCompat.notify(1,notificationCompatBuilder.build())
                            }
                        }
                    }
                }
            }
        }
    }
}