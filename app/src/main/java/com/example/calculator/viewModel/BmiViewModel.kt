package com.example.calculator.viewModel


import androidx.lifecycle.ViewModel
import com.example.calculator.uiState.BMiuiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.properties.Delegates

class BmiViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(BMiuiState())
    val uiState : StateFlow<BMiuiState> = _uiState.asStateFlow()
    private var _bmi=MutableStateFlow(0.0)
    val bmi :StateFlow<Double> = _bmi.asStateFlow()
    fun changeValues(updatedValues:List<Int?>){
        _uiState.value=BMiuiState(height = updatedValues[0], weight = updatedValues[1])
    }
    fun decrementHeight(){
       if(uiState.value.height!=0)
            _uiState.value=BMiuiState(height = uiState.value.height?.minus(1), weight = uiState.value.weight)

    }

    fun decrementWeight(){
        if(uiState.value.weight!=0)
            _uiState.value=BMiuiState(height = uiState.value.height, weight = uiState.value.weight?.minus(1))
    }

    fun incrementHeight(){
        if(uiState.value.height ==null)
            _uiState.value=BMiuiState(height = 1, weight = uiState.value.weight)
        else
            _uiState.value=BMiuiState(height = uiState.value.height?.plus(1), weight = uiState.value.weight)
    }

    fun incrementWeight(){
        if(uiState.value.weight == null)
            _uiState.value=BMiuiState(height = uiState.value.height, weight = 1)
        else
            _uiState.value=BMiuiState(height = uiState.value.height, weight = uiState.value.weight?.plus(1))
    }

    fun calculateBMI():String?{
        if(uiState.value.height == null) return "Height"
        else if(uiState.value.weight == null) return "Weight"
        else{
            _bmi.value=_calculateBMI(uiState.value.height!!, uiState.value.weight!!)
        }
        return null
    }

    private fun _calculateBMI(height:Int,weight:Int):Double{
        val bmi:Double= (weight)/(height*0.01*height*0.01)
        return bmi
    }

}