package com.example.currencyapp.domain.event

sealed class CurrencyResult<T>(
    val data : T? = null,
    val message : String? = null
){
    class Success<T>(data: T) : CurrencyResult<T>(data = data)
    class Failure<T>(data: T?,message: String) : CurrencyResult<T>(data = data,message = message)
}