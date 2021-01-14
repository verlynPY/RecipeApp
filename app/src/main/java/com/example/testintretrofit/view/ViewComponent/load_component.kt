package com.example.testintretrofit.view.ViewComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview


@Composable
fun CircularProgressBar(Enable: Boolean){
    if(Enable){
        Row(modifier = Modifier.fillMaxWidth()
                .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
        ){
            CircularProgressIndicator(color = Color(239, 200, 8))
        }
    }
}