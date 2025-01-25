package com.example.calculator.views.QuadSolver

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun Preview(){
    QuadSolverPage()
}

@Composable
fun QuadSolverPage(modifier: Modifier = Modifier) {
    val valueOfA = remember { mutableStateOf("") }
    val valueOfB = remember { mutableStateOf("") }
    val valueOfC = remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopEquation(
            modifier=Modifier.border(2.dp,shape= RectangleShape,color= Color.Red)
                .padding(10.dp),
            valueOfA.value,
            valueOfB.value,
            valueOfC.value
        )
        Spacer(modifier=Modifier.height(10.dp))
        InputFields(valueArray = arrayOf(valueOfA,valueOfB,valueOfC))
        Spacer(modifier=Modifier.height(10.dp))


        if(valueOfA.value.isNotEmpty() && valueOfB.value.isNotEmpty() && valueOfC.value.isNotEmpty())
            ResultValues(
                modifier=Modifier.border(2.dp,shape= RectangleShape,color=Color.Red),
                valueOfA = valueOfA.value.toDouble(),
                valueOfB=valueOfB.value.toDouble(),
                valueOfC = valueOfC.value.toDouble()
            )

    }
}
