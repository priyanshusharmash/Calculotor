package com.example.calculator.views.QuadSolver

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun Preview1(){
    TopEquation()
}

@Composable
fun TopEquation(){
    var expression= buildAnnotatedString {
        append("Ax")
        withStyle(
            style= SpanStyle(
                baselineShift = BaselineShift.Superscript,
                fontSize = 10.sp)
        ){
            append("2")
        }
    }
    Row(){
        Text(
            text = expression
        )
    }
}