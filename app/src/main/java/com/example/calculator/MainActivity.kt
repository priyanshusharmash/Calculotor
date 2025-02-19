package com.example.calculator


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.views.MainPage
import com.example.calculator.views.QuadSolver.QuadSolverPage
import com.example.calculator.views.bmiCalculator.BMICalculatorPage
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
               Surface {
                   var isSplashScreen by rememberSaveable { mutableStateOf(true) }
                   if(Build.VERSION.SDK_INT<31) {
                       if(isSplashScreen){
                           SplashScreen()
                           LaunchedEffect(Unit) {
                               delay(1000)
                               isSplashScreen=false
                           }
                       }else{
                           MainPage()
                       }
                   }else{
                       MainPage()
                   }
               }
            }
        }
    }

}




