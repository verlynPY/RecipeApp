package com.example.testintretrofit.view

import NavigationRecipe
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Utils
import com.example.testintretrofit.model.Utils.GoBack
import com.example.testintretrofit.view.ui.TestintretrofitTheme
import com.example.testintretrofit.view.ui.shapes
import retrofit2.http.Query

@ExperimentalLazyDsl
class NavigationViewRecipe : AppCompatActivity() {
    @ExperimentalLazyDsl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Scaffold(
                    topBar = {
                        TopAppBar(backgroundColor = Color(0, 0, 0)) {
                            IconButton(onClick = {
                                val mainViewRecipe = MainViewRecipe()
                                overridePendingTransition(0, 0)
                                GoBack(applicationContext, mainViewRecipe)
                                overridePendingTransition(0, 0)
                            }) {
                                Icon(Icons.Filled.Home, tint = Color(239, 200, 8))
                            }
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                                    Text("Explore", modifier = Modifier.absolutePadding(left = 10.dp),style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp), textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }
                    },
                    bodyContent = {

                               ScrollableColumn(Modifier.fillMaxSize()) {
                                    Column() {
                                        NavigationRecipe(applicationContext)
                                    }
                                }



                    })
        }
    }
}
