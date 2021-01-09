package com.example.testintretrofit.view

import CardRecipee
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.state
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testintretrofit.model.Recipe
import com.example.testintretrofit.model.hitsobj
import com.example.testintretrofit.viewmodel.MainViewModel
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@ExperimentalLazyDsl
class MainViewRecipe : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val vModel: MainViewModel by viewModels()
    private var listRecipe:ArrayList<hitsobj> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var obj: MutableList<hitsobj?>? = null



            viewModel.getFirstTodo().observe(this@MainViewRecipe, Observer {
                obj = it.hit
                for (receta in obj!!) {
                    listRecipe.add(receta!!)
                }
                val result = listRecipe
                val fabShape = CircleShape
                setContent {
                    val state = state { "" }

                    Scaffold(

                            bottomBar = {
                                BottomAppBar(backgroundColor = Color(0, 0, 0)) {

                                    IconButton(onClick = {
                                        // scaffoldState.drawerState.open()
                                    }) {
                                        Icon(Icons.Filled.Favorite, tint = Color(239, 200, 8), modifier = Modifier.preferredHeight(80.dp))

                                    }
                                    IconButton(onClick = {
                                        // scaffoldState.drawerState.open()
                                    }) {
                                        Icon(Icons.Filled.Home, tint = Color(239, 200, 8))

                                    }
                                    IconButton(onClick = {
                                        OpenNavigation()
                                    }) {
                                        Icon(Icons.Filled.Search, tint = Color(239, 200, 8))

                                    }

                                }
                            },
                            bodyContent = {

                                LazyColumn {
                                    itemsIndexed(items = result) { index, result ->
                                        result.recipe.label?.let { it1 -> result.recipe.image?.let { it2 -> CardRecipee(applicationContext,it1, it2, onClick = { OpenDetailsRecipe(result.recipe) }) } }

                                    }
                                }

                            })


                }
            })




    }


    fun OpenDetailsRecipe(recipe: Recipe){
        val intent = Intent(applicationContext, DetailsViewRecipe::class.java)
        val bundle = Bundle()
        bundle.putString("Label",recipe.label)
        bundle.putStringArrayList("Ingredientes", recipe.ingredientLines)
        bundle.putString("Image",recipe.image)
        bundle.putFloat("Calorias", recipe.calories)
        intent.putExtras(bundle)

            startActivity(intent)

    }
    fun OpenNavigation(){
        val intent = Intent(applicationContext, NavigationViewRecipe::class.java)
        startActivity(intent)
    }
}