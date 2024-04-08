package com.example.currencyapp.domain.repository

import com.example.currencyapp.domain.Model.Currency
import com.example.currencyapp.domain.event.CurrencyResult
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository{
    suspend fun getLiveCurrency() : Flow<CurrencyResult<Currency>>
}