package com.example.calculator


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun SplashScreen(){
    Scaffold { innerPadding->
        Column(
            modifier=Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5F)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.image), contentDescription = "app_icon", modifier = Modifier.fillMaxHeight(0.3F))
            }

            Column(
                modifier=Modifier

                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(R.drawable.branding_image),
                    contentDescription = "branding_image",
                    modifier = Modifier
                        .fillMaxHeight(0.3F)
                        .fillMaxWidth(0.6F)
                        .padding(bottom = 20.dp),
                    contentScale = ContentScale.Fit)
            }

        }
    }

}

