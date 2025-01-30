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

private const val TAG = "Main Activity"
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d(TAG,"onCreate called")
        setContent {
            CalculatorTheme {
               Surface {
                   MainPage()
               }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG,"onResume called")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy called")
    }
}




