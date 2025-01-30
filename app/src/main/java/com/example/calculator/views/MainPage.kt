package com.example.calculator.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.calculator.views.Calculator.ArithmeticCalculator
import com.example.calculator.views.QuadSolver.QuadSolverPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainPage(){
    val coroutineScope= rememberCoroutineScope()
    val pagerState= rememberPagerState(pageCount = { Tabs.entries.size})
    val selectedTabIndex by remember { mutableStateOf(0) }
    Scaffold (

    ){innerPadding->
        Column (modifier= Modifier
            .padding(innerPadding)
            .fillMaxSize())
        {
            TabsRow(pagerState,coroutineScope,selectedTabIndex)
            Horizontal_Pager(pagerState)
        }
    }

}


@Composable
fun TabsRow(pagerState: PagerState,coroutineScope:CoroutineScope,selectedTabIndex:Int){
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.fillMaxWidth()
    ) {
        Tabs.entries.forEachIndexed { index, tab ->
            Tab(
                selected = index==pagerState.currentPage,
                onClick = {coroutineScope.launch { pagerState.animateScrollToPage(index) }},
                text = { Text(text = tab.name) }
            )
        }
    }
}

@Composable
fun Horizontal_Pager(pagerState: PagerState){
    HorizontalPager(
        beyondViewportPageCount = Tabs.entries.size,
        state = pagerState,
        userScrollEnabled = true,
    ) { page->
        when(page){
            0 -> ArithmeticCalculator()
            1 -> QuadSolverPage()
        }
    }
}


