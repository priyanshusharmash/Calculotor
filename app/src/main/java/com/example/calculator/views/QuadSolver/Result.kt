package com.example.calculator.views.QuadSolver

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat
import kotlin.math.sqrt


@Composable
fun ResultValues(modifier:Modifier=Modifier,valueOfA: Double, valueOfB:Double, valueOfC:Double){
    val (x1, x2) = calculateResult(valueOfA, valueOfB, valueOfC)
    val labelArray=arrayOf("x1 : ","x2 : ")
    Column (modifier=modifier){
        if(x1.isNotEmpty() && x2.isNotEmpty()){
            labelArray.forEachIndexed{index,label->
                TextRow(label,if(index==0) x1 else x2)
            }
        }
    }

}

@Composable
fun TextRow(label:String,answer:String){
    Row (modifier=Modifier.padding(10.dp)){
        Text(
            text = label
        )
        if(answer.isNotEmpty()){
            Text(
                text = answer
            )
        }
    }
}

@SuppressLint("DefaultLocale")
fun calculateResult(valueOfA:Double, valueOfB:Double, valueOfC:Double):Pair<String,String>{

    val df=DecimalFormat("#.##")
    try {
        val d=(valueOfB*valueOfB)-(4*valueOfA*valueOfC)
        if(d>0){
            val x1=(-valueOfB+ sqrt(d))/(2*valueOfA)
            val x2=(-valueOfB-sqrt(d))/(2*valueOfA)
            return Pair(df.format(x1),df.format(x2))
        }
        else if(d==0.0){
            val x =(-valueOfB)/(2*valueOfA)
            return Pair(df.format(x),df.format(x))
        }
        else{
            val a= -valueOfB/(2*valueOfA)
            val b= sqrt(4*valueOfA*valueOfC - valueOfB*valueOfB)/(2*valueOfA)
            val x1= df.format(a)
            val x2 = df.format(b)
            return Pair("$x1 + ${x2}i","$x1 - ${x2}i")

        }
    }catch (e:Exception) {
        return Pair("Error","Error")
    }

}