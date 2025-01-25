package com.example.calculator.views.QuadSolver


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun InputFields(modifier:Modifier=Modifier,valueArray:Array<MutableState<String>>){
    val labelArray=arrayOf("A","B","C")
    Column (modifier=modifier)
    {
        labelArray.forEachIndexed{ index , label->
            InputRow(
                modifier=Modifier.padding(10.dp),
                label = label,
                value= valueArray[index].value,
                onValueChange = { changedValue->
                    valueArray[index].value = changedValue
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = if(index==0) ImeAction.Next else if(index==1) ImeAction.Next else ImeAction.Done
                )
            )
        }
    }
}

@Composable
fun InputRow(modifier:Modifier=Modifier,label:String,value: String ,onValueChange:(String)->Unit,keyboardOptions: KeyboardOptions){
    Row(modifier=modifier){
        Text(
            text = "$label : ",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            maxLines = 1,
            keyboardOptions = keyboardOptions
        )
    }
}
