package com.example.calculator.views.bmiCalculator


import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.calculator.R
import androidx.compose.material3.ButtonElevation as ButtonElevation1


@Composable
fun GenderButton(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    text: String,
    colors: ButtonColors,
    shape: Shape,
    onClicked: (String) -> Unit,
    activeButton: String
) {
    Button(
        onClick = { onClicked(text) },
        shape = shape,
        colors = colors,
        modifier = modifier,
        enabled = activeButton != text
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(icon), contentDescription = " ")
            Text(text = text)
        }

    }
}


@Composable
fun ValuesButton(
    title: String, unit: String,
    modifier: Modifier = Modifier,
    itemValue: String,
    onItemValueChanged: (String) -> Unit,
    onItemValueDecrement: () -> Unit,
    onItemValueIncrement: () -> Unit
) {
    val textButtonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
        disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
    )

    val textButtonModifier = Modifier
        .padding(5.dp)
        .shadow(elevation = 5.dp, shape = CircleShape)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        Spacer(Modifier.height(10.dp))
        Text(text = title)
        Spacer(Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = itemValue,
                onValueChange = { onItemValueChanged(it) },
                modifier = Modifier.fillMaxWidth(0.6F),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(Modifier.width(5.dp))
            Text(text = unit)
        }
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = onItemValueDecrement,
                modifier = textButtonModifier,
                shape = CircleShape,
                colors = textButtonColors,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_24),
                        contentDescription = ""
                    )
                })


            TextButton(
                modifier = textButtonModifier,
                onClick = onItemValueIncrement,
                shape = CircleShape,
                colors = textButtonColors,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_add_24),
                        contentDescription = ""
                    )
                })
        }
    }
}

@Composable
fun CalculateButton(
    text:String,
    onButtonClick :() ->Unit,
    buttonModifier:Modifier=Modifier,
    textModifier:Modifier=Modifier,
    textStyle:TextStyle= TextStyle(),
    shape:Shape,
    buttonElevation: ButtonElevation,
){
    Button(
        onClick = onButtonClick,
        shape = shape,
        elevation =buttonElevation,
        content = {
            Text(
                text=text,
                modifier = textModifier,
                style = textStyle)
        },
        modifier = buttonModifier,
    )
}