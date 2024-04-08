package com.example.currencyapp.data.remote

import com.example.currencyapp.domain.Model.Currency
import retrofit2.http.GET

interface CurrencyApi{
    @GET("currentprice.json")
    suspend fun getCurrency() : Currency

    companion object{
        const val BASE_URL = "https://api.coindesk.com/v1/bpi/"
    }
}