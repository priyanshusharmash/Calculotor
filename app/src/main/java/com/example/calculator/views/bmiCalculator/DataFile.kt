package com.example.calculator.views.bmiCalculator

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Gender(
    @StringRes
    val gender:Int,
    @DrawableRes
    val icon:Int
)

data class Parameter(
    @StringRes
    val criteria:Int,
    @StringRes
    val unit:Int,
    val value:Int? = 0
)