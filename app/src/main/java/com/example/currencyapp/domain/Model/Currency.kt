package com.example.currencyapp.domain.Model



data class Currency(
    val time : Time,
    val disclaimer : String,
    val chartName : String,
    val bpi : Bpi
)
