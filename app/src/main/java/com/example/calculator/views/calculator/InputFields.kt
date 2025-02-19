package com.example.calculator.views.calculator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun ExpressionInputField(
    expression: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    style: TextStyle
) {
    var currentText by remember {
        mutableStateOf(
            TextFieldValue(
                expression,
                TextRange(expression.length)
            )
        )
    }
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(expression) {
        currentText = TextFieldValue(expression, TextRange(expression.length))
        scope.launch {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }
    Box(modifier=modifier
        .padding(2.dp)
        .border(
            width = 1.dp,
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.primary
        ),
        contentAlignment = Alignment.BottomEnd
    ){
        OutlinedTextField(
            value = currentText,
            onValueChange = { newText ->
                scope.launch {
                    scrollState.scrollTo(scrollState.maxValue)
                }
                currentText = newText.copy(
                    selection = TextRange(newText.text.length)
                )
                onTextChange(newText.text)

            },
            textStyle = style,
            readOnly = true,
            maxLines = 3,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                unfocusedBorderColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}







@Composable
fun AnswerField(modifier: Modifier = Modifier, answer: String, style: TextStyle) {
    Box(
        modifier=modifier,
        contentAlignment = Alignment.CenterEnd
    ){
        Text(
            text = answer,
            textAlign = TextAlign.End,
            style = style,
            modifier= Modifier.padding(5.dp)
        )
    }

}
