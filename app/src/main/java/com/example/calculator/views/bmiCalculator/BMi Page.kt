package com.example.calculator.views.bmiCalculator


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.R
import com.example.calculator.viewModel.BmiViewModel
import java.text.DecimalFormat


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BMICalculatorPage(
    viewModel: BmiViewModel = viewModel(),
    context:Context= LocalContext.current
) {

    val uiState by viewModel.uiState.collectAsState()
    var showResultDialog by remember { mutableStateOf(false) }
    val parameters = listOf(
            Parameter(R.string.height, R.string.cm, uiState.height),
            Parameter(R.string.weight, R.string.kg, uiState.weight)
        )
    val dialogModifier= Modifier
        .fillMaxHeight(0.1F)
        .shadow(elevation = 10.dp, shape = RoundedCornerShape(15.dp))
        .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(15.dp))
        .padding(10.dp)
    val unFormatedBMI= viewModel.bmi.value
    val df=DecimalFormat("#.##")
    val bmi=df.format(unFormatedBMI)
    val calculateButtonTextStyle= TextStyle(
        fontStyle =MaterialTheme.typography.bodyLarge.fontStyle,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing
    )
    val calculateButtonModifier= Modifier
        .padding(20.dp)
        .fillMaxHeight(0.2F)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GenderOption()

        Spacer(Modifier.height(20.dp))

        Parameters(
            context = context,
            parameters = parameters,
            viewModel = viewModel
        )

        Spacer(Modifier.height(20.dp))

        CalculateButton(
            text = context.getString(R.string.calculate),
            onButtonClick = {
                val nullValue=viewModel.calculateBMI()
                if (nullValue == context.getString(R.string.height)) Toast.makeText(
                    context,
                    "${context.getString(R.string.height)} ${context.getString(R.string.bmiToast)}",
                    Toast.LENGTH_SHORT
                ).show()
                else if (nullValue == context.getString(R.string.weight)) Toast.makeText(
                    context,
                    "${context.getString(R.string.weight)} ${context.getString(R.string.bmiToast)}",
                    Toast.LENGTH_SHORT
                ).show()
                else{
                    showResultDialog=true
                }

            },
            textStyle =calculateButtonTextStyle ,
            buttonModifier =calculateButtonModifier,
            shape = RoundedCornerShape(15.dp),
            buttonElevation = ButtonDefaults.elevatedButtonElevation(if(!showResultDialog)20.dp else 0.dp),
        )

        if(showResultDialog){
            ShowResultDialog(
                onDismissRequest = {showResultDialog=!showResultDialog},
                modifier=dialogModifier,
                text = context.getString(R.string.bmiDialogText),
                bmi = bmi
            )
        }
    }
}

@Composable
fun GenderOption(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val genderList = listOf(
        Gender(R.string.male, R.drawable.baseline_male_24),
        Gender(R.string.female, R.drawable.baseline_female_24)
    )
    var activeButton by remember { mutableStateOf("") }
    val genderButtonColors = ButtonColors(
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
        disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
    )
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2F),
    ) {
        genderList.forEachIndexed { _, gender ->
            val buttonModifier = if (activeButton == context.getString(gender.gender)) {
                Modifier
                    .fillMaxHeight()
                    .weight(1F)
                    .padding(25.dp)
            } else {
                Modifier
                    .fillMaxHeight()
                    .weight(1F)
                    .padding(25.dp)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
            }

            GenderButton(
                icon = gender.icon,
                text = context.resources.getString(gender.gender),
                modifier = buttonModifier,
                shape = RoundedCornerShape(10.dp),
                colors = genderButtonColors,
                onClicked = { activeButton = it },
                activeButton = activeButton
            )
        }
    }
}

@Composable
fun Parameters(
    context: Context = LocalContext.current,
    parameters: List<Parameter>,
    viewModel: BmiViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        parameters.forEachIndexed { index, parameter ->
            ValuesButton(
                title = context.getString(parameter.criteria),
                unit = context.getString(parameter.unit),
                modifier = Modifier
                    .weight(1F)
                    .padding(10.dp)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceContainer
                    ),
                itemValue = if (parameter.value == null) "" else parameter.value.toString(),
                onItemValueChanged = {
                    val newValue = if (it.isNotEmpty()) it.toInt() else null
                    when (index) {
                        0 -> viewModel.changeValues(listOf(newValue, parameters[1].value))
                        1 -> viewModel.changeValues(listOf(parameters[0].value, newValue))
                    }
                },
                onItemValueDecrement = {
                    when (index) {
                        0 -> viewModel.decrementHeight()
                        1 -> viewModel.decrementWeight()
                    }
                },
                onItemValueIncrement = {
                    when (index) {
                        0 -> viewModel.incrementHeight()
                        1 -> viewModel.incrementWeight()
                    }
                }
            )
        }
    }
}



@Composable
fun ShowResultDialog(text:String,bmi:String,modifier:Modifier=Modifier,onDismissRequest:()->Unit){
    Dialog(
        onDismissRequest =onDismissRequest
    ) {
        Column (
            modifier=modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ){
            Text(text ="$text $bmi")
        }
    }

}