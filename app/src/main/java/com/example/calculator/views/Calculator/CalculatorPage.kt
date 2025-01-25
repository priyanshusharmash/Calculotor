package com.example.calculator.views.Calculator



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal



@Composable
fun ArithmeticCalculator(){
    val expression = remember{ mutableStateOf("") }
    val answer = remember{ mutableStateOf("") }
    val previousAnswer= remember { mutableStateOf("") }

    BoxWithConstraints(modifier=Modifier.fillMaxSize()) {
        val maxHeight=maxHeight
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .height(maxHeight)
        ){

            ExpressionInputField(

                modifier = Modifier
                    ,
                expression = expression.value,

                style = TextStyle(
                    fontSize =  MaterialTheme.typography.displayLarge.fontSize,
                    textAlign = TextAlign.End
                ),
                onTextChange = { newExpression->
                    expression.value=newExpression
                }
            )


            AnswerField(
                modifier=Modifier.fillMaxWidth()
                    .height(maxHeight*0.1F)

                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(10.dp),

                answer = answer.value,
                style = MaterialTheme.typography.displaySmall
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


fun buttonDecision(buttonClicked:String, expression:MutableState<String>, result:MutableState<String>,previousAns:MutableState<String>){
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

fun calculateAnswer(expression:String,result:(String)->Unit){
    try {
        val Result = ExpressionBuilder(expression).build().evaluate()
        val answerStr=BigDecimal(Result).stripTrailingZeros().toPlainString()

        result(if(answerStr.contains('.') && answerStr.substringAfter('.').all { it =='0' }) answerStr.toInt().toString() else answerStr)
    }catch (e:Exception){
        result("Error")
    }
}