package com.example.currencyapp.data.repository

import com.example.currencyapp.data.remote.CurrencyApi
import com.example.currencyapp.domain.Model.Currency
import com.example.currencyapp.domain.event.CurrencyResult
import com.example.currencyapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi
): CurrencyRepository {
    override suspend fun getLiveCurrency(): Flow<CurrencyResult<Currency>> {
        return flow {
            val currencyData = try {
                currencyApi.getCurrency()
            }catch (e : Exception){
                emit(CurrencyResult.Failure(data = null,message = "Check your Connection"))
                return@flow
            }
            emit(CurrencyResult.Success(data = currencyData))
        }
    }

}


