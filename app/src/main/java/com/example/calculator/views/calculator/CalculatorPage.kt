package com.example.calculator.views.calculator



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal
import java.text.DecimalFormat


@Composable
fun ArithmeticCalculator(){
    val expression = remember{ mutableStateOf("") }
    val answer = remember{ mutableStateOf("") }
    val previousAnswer= remember { mutableStateOf("") }

    BoxWithConstraints(modifier=Modifier.fillMaxSize()) {
        val maxHeight=maxHeight
        val maxWidth = maxWidth
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .height(maxHeight)
        ){

            ExpressionInputField(
                expression = expression.value,
                style = TextStyle(
                    fontSize =  MaterialTheme.typography.displayLarge.fontSize,
                    textAlign = TextAlign.End
                ),
                onTextChange = { newExpression->
                    expression.value=newExpression
                },
                modifier = Modifier.weight(1F)
                    .width(maxWidth)
            )


            AnswerField(
                modifier=Modifier.fillMaxWidth()
                    .padding(2.dp)
                    .height(maxHeight*0.1F)
                    .border(width = 1.dp,shape= RoundedCornerShape(5.dp), color = MaterialTheme.colorScheme.primary),

                answer = answer.value,
                style = MaterialTheme.typography.displayMedium
            )

            Grid(modifier = Modifier.height(maxHeight*0.60F)){ buttonPressed->
                buttonDecision(
                    buttonClicked = buttonPressed,
                    expression = expression,
                    result = answer,
                    previousAns = previousAnswer
                )

            }

        }
    }

}


private fun buttonDecision(buttonClicked:String, expression:MutableState<String>, result:MutableState<String>,previousAns:MutableState<String>){
    when(buttonClicked){
        "=" ->
        {
            calculateAnswer(expression.value, result = {result.value= "=$it" })
            if(result.value!="=Error")  previousAns.value=result.value.drop(1)
        }
        "AC" ->{

            expression.value=""
            result.value=""
        }
        "DEL" -> expression.value=expression.value.dropLast(1)
        "Ans" -> if(previousAns.value.isNotEmpty()) expression.value=previousAns.value
        else ->expression.value+=buttonClicked
    }
}

private fun calculateAnswer(expression:String,result:(String)->Unit){
    try {
        val calculatedResult = ExpressionBuilder(expression).build().evaluate()
        val decimalFormat = DecimalFormat("##.###")
        val formatedResult = decimalFormat.format(calculatedResult)
        val answerStr=BigDecimal(formatedResult).stripTrailingZeros().toPlainString()

        result(if(answerStr.contains('.') && answerStr.substringAfter('.').all { it =='0' }) answerStr.toInt().toString() else answerStr)
    }catch (e:Exception){
        result("Error")
    }
}