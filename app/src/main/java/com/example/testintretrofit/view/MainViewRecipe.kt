package com.example.testintretrofit.view

import CardRecipee
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testintretrofit.model.Recipe
import com.example.testintretrofit.model.Utils
import com.example.testintretrofit.model.Utils.Connection
import com.example.testintretrofit.model.Utils.OpenDetailsRecipe
import com.example.testintretrofit.model.Utils.OpenFavorite
import com.example.testintretrofit.model.Utils.OpenNavigation
import com.example.testintretrofit.model.hitsobj
import com.example.testintretrofit.view.ViewComponent.CircularProgressBar
import com.example.testintretrofit.viewmodel.MainViewModel
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
@ExperimentalLazyDsl
class MainViewRecipe : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private var listRecipe:ArrayList<hitsobj> = ArrayList()
    private var dataisloading:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val Lyrics = 'a'..'z'
        var Lyrics_Random = Lyrics.random()
      viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var obj: MutableList<hitsobj?>? = null
            viewModel.getFirstTodo(Lyrics_Random.toString()).observe(this@MainViewRecipe, Observer {
                obj = it.hit
                for (receta in obj!!) {
                    listRecipe.add(receta!!)
                    dataisloading = false
                }
                val result = listRecipe
                setContent {
                    Scaffold(
                            bottomBar = {
                                BottomAppBar(backgroundColor = Color(0, 0, 0)) {
                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                        IconButton(modifier = Modifier.absolutePadding(10.dp, 0.dp, 10.dp, 0.dp), onClick = {
                                            var favoriteViewRecipe = FavoriteViewRecipe()
                                            OpenFavorite(context = applicationContext, favoriteViewRecipe)
                                            overridePendingTransition(0, 0)
                                        }) {
                                            Icon(Icons.Filled.Favorite, tint = Color(239, 200, 8))

                                        }
                                        IconButton(modifier = Modifier.absolutePadding(10.dp, 0.dp, 10.dp, 0.dp), onClick = {
                                            finish()
                                            overridePendingTransition(0, 0)
                                            startActivity(intent)
                                            overridePendingTransition(0, 0)

                                        }) {
                                            Icon(Icons.Filled.Home, tint = Color(239, 200, 8))

                                        }
                                        IconButton(modifier = Modifier.absolutePadding(10.dp, 0.dp, 10.dp, 0.dp), onClick = {
                                           var navigationViewRecipe = NavigationViewRecipe()
                                            OpenNavigation(context = applicationContext,navigationViewRecipe)
                                            overridePendingTransition(0, 0)
                                        }) {
                                            Icon(Icons.Filled.Search, tint = Color(239, 200, 8))

                                        }
                                    }

                                }
                            },
                            bodyContent = {
                                    LazyColumn {
                                        itemsIndexed(items = result) { index, result ->
                                            CardRecipee(false, applicationContext, result.recipe, onClick = { OpenDetailsRecipe(context = applicationContext,result.recipe) })
                                        }
                                    }
                            })
                }
            })
    }



}