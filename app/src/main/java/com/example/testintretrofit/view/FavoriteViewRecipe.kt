package com.example.testintretrofit.view

import CardRecipee
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Recipe
import com.example.testintretrofit.model.Utils.OpenDetailsRecipe
import com.example.testintretrofit.viewmodel.MainViewModel

@ExperimentalLazyDsl
class FavoriteViewRecipe : AppCompatActivity() {
    lateinit var viewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val data: ArrayList<Recipe>? = ArrayList()
        viewmodel.GetFavorite().observe(this, Observer {
            data!!.add(it)
                setContent {
                    Scaffold(topBar = {
                        TopAppBar(backgroundColor = Color(0, 0, 0)) {
                            IconButton(onClick = {
                                finish()
                                overridePendingTransition(0,0)
                            }) {
                                Icon(
                                        asset = vectorResource(id = R.drawable.previous), tint = Color(239, 200, 8))
                            }
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                                    Text("Favorites", modifier = Modifier.absolutePadding(left = 10.dp),style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp), textAlign = TextAlign.Start
                                    )
                                }
                            }
                        }
                    }, bodyContent = {
                            LazyColumn {
                                itemsIndexed(items = data) { index, data ->
                                    CardRecipee(true, context = applicationContext, recipe = data, onClick = { OpenDetailsRecipe(applicationContext,data) })
                                }
                            }
                    })
                }

        })
    }
}
