package com.example.calculator.views

import androidx.compose.runtime.Composable
import com.example.calculator.views.calculator.ArithmeticCalculator
import com.example.calculator.views.QuadSolver.QuadSolverPage
import com.example.calculator.views.bmiCalculator.BMICalculatorPage

enum class Tabs(val index:Int, val composableFunction : @Composable () ->  Unit ){
    BMI(0, {BMICalculatorPage()}),
    Calculator(1, {ArithmeticCalculator()}),
    QuadSolver(2, {QuadSolverPage()});

    companion object {
        fun fromIndex(value: Int): Tabs? {
            return entries.find { it.index == value }
        }
    }
}