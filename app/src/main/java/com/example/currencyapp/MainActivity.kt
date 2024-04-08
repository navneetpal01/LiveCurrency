package com.example.currencyapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.currencyapp.presentation.CurrencyScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vieModel by viewModels<MainViewModel>()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val currencyData = vieModel.currencyData.collectAsState().value
            currencyData?.let { currency ->
                CurrencyScreen(bpi = currency.bpi)
            }
            LaunchedEffect(key1 = vieModel.showToast) {
                vieModel.showToast.collectLatest { showToast ->
                    if (showToast) {
                        Toast.makeText(
                            context,
                            "Check your Internet",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }
    }
}





