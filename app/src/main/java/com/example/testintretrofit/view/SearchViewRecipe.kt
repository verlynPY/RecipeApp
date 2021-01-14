package com.example.testintretrofit.view

import CardRecipee
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.TextSemanticsProperties.ImeAction
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.ui.tooling.preview.Preview
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Recipe
import com.example.testintretrofit.model.Utils.GoBack
import com.example.testintretrofit.model.Utils.OpenDetailsRecipe
import com.example.testintretrofit.model.hitsobj
import com.example.testintretrofit.viewmodel.MainViewModel


@ExperimentalLazyDsl
class SearchViewRecipe : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val vModel: MainViewModel by viewModels()
    private var listRecipe:ArrayList<hitsobj> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val query = bundle!!.getCharSequence("Query")
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var obj: MutableList<hitsobj?>? = null
        viewModel.GetSearchRecipe(query.toString()).observe(this, Observer {
            obj = it.hit
            for (receta in obj!!) {
                listRecipe.add(receta!!)
            }
            var result = listRecipe
            setContent {
                Scaffold(
                    topBar = {
                        TopAppBar(backgroundColor = Color(0, 0, 0)) {
                            IconButton(onClick = {
                                val navigationViewRecipe: NavigationViewRecipe= NavigationViewRecipe()
                                GoBack(context = applicationContext,activity2 = navigationViewRecipe)
                            }) {
                                Icon(
                                    asset = vectorResource(id = R.drawable.previous),tint = Color(239, 200, 8)
                                )
                            }
                            Column(modifier = Modifier.fillMaxSize()) {
                                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                            Text(query.toString(), modifier = Modifier.padding(10.dp).absolutePadding(left = 10.dp),textAlign = TextAlign.Center,
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
                            )
                                }
                            }
                        }
                    },
                    bodyContent = {
                        LazyColumn {
                            itemsIndexed(items = result) { index, result ->
                                CardRecipee(false,applicationContext,
                                    recipe = result.recipe,
                                    onClick = {
                                        OpenDetailsRecipe(context = applicationContext,result.recipe)
                                    })
                            }
                        }
                    })
            }
        })
    }
}