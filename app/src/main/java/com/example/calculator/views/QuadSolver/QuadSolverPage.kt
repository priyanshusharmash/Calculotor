package com.example.calculator.views.QuadSolver


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun QuadSolverPage(modifier: Modifier = Modifier) {
    val valueOfA = rememberSaveable { mutableStateOf("") }
    val valueOfB = rememberSaveable { mutableStateOf("") }
    val valueOfC = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopEquation(
            modifier=Modifier
                .padding(10.dp)
                .fillMaxHeight(0.2F)
                .fillMaxWidth(),
            valueOfA.value,
            valueOfB.value,
            valueOfC.value
        )
        Spacer(modifier=Modifier.height(10.dp))
        InputFields(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            valueArray = arrayOf(valueOfA,valueOfB,valueOfC)
        )
        Spacer(modifier=Modifier.height(20.dp))


        if(valueOfA.value.isNotEmpty() && valueOfB.value.isNotEmpty() && valueOfC.value.isNotEmpty())
            ResultValues(
                modifier=Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                valueOfA = valueOfA.value.toDouble(),
                valueOfB=valueOfB.value.toDouble(),
                valueOfC = valueOfC.value.toDouble()
            )

    }
}
