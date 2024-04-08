package com.example.currencyapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.currencyapp.domain.Model.Bpi
import com.example.currencyapp.domain.Model.CurrencyDetails

@Composable
fun CurrencyScreen(
    bpi: Bpi
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Currency(currencyDetails = bpi.EUR)
        Currency(currencyDetails = bpi.USD)
        Currency(currencyDetails = bpi.GBP)
    }
}

@Composable
fun Currency(currencyDetails: CurrencyDetails) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = currencyDetails.code)
        Text(text = currencyDetails.rate)
        Text(text = currencyDetails.symbol)
        Text(text = currencyDetails.description)
    }
}