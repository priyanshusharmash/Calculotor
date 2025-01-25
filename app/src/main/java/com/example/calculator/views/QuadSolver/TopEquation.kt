package com.example.calculator.views.QuadSolver

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp


@Composable
fun TopEquation(modifier:Modifier=Modifier, a:String, b:String, c:String){

    val expression= buildAnnotatedString {
        append(if(a.isNotEmpty())"${a}x" else "Ax")
        withStyle(
            style= SpanStyle(
                baselineShift = BaselineShift.Superscript,
                fontSize = 10.sp)
        ){
            append("2")
        }
        append(if(b.isNotEmpty())"+${b}x" else "+Bx")
        append(if(c.isNotEmpty()) "+${c}=0" else "+C=0" )
    }
    Row(modifier=modifier){
        Text(
            text = expression
        )
    }
}