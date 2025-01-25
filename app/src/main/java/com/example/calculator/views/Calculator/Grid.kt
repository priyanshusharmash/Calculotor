package com.example.calculator.views.Calculator



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Grid(modifier:Modifier=Modifier,buttonPressed:(String)->Unit){

    var buttons= GridRepository()
    BoxWithConstraints (
        modifier=modifier
    ){
      val maxHeight=with(LocalDensity.current){constraints.maxHeight.toDp()}
      LazyVerticalStaggeredGrid(
          columns=StaggeredGridCells.Fixed(4),
          horizontalArrangement = Arrangement.Center
      ) {
          items(buttons){button->
              val cellHeight=if(button=="=") (maxHeight/5)*2 else maxHeight/5
              GridButton(
                  button,
                  onClick={buttonPressed(button)},
                  modifier = Modifier.height(cellHeight)
              )
          }
      }
    }

}


fun GridRepository():List<String>{
    return  listOf(
        "AC","DEL","/","*",
        "7","8","9","-",
        "4","5","6","+",
        "1","2","3","=",
        ".","0","Ans"
    )
}
