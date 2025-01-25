package com.example.calculator.views.Calculator

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape


@Composable
fun GridButton(
    value:String,
    modifier:Modifier=Modifier,
    onClick: ()->Unit){
    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = modifier)
    {
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}