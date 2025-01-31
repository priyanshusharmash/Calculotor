package com.example.calculator


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.views.MainPage
import com.example.calculator.views.QuadSolver.QuadSolverPage
import com.example.calculator.views.bmiCalculator.BMICalculatorPage


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
               Surface {
                  MainPage()

               }
            }
        }
    }

}




