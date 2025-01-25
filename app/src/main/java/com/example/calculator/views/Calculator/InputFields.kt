package com.example.calculator.views.Calculator

import android.graphics.Paint.Style
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.exp
import kotlin.math.min


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



        TextField(
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
            maxLines = 2,
            minLines = 2,
            modifier = modifier
        )



}







@Composable
fun AnswerField(modifier: Modifier = Modifier, answer: String, style: TextStyle) {
    Text(
        text = answer,
        modifier = modifier,
        textAlign = TextAlign.Right,
        style = style
    )
}
