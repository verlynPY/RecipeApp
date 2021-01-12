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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.ui.tooling.preview.Preview
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Recipe
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
            val fabShape = CircleShape
            //   val scaffoldState = rememberScaffoldState()
            setContent {
                val state = state { "" }

                Scaffold(

                    topBar = {
                        TopAppBar(backgroundColor = Color(0, 0, 0)) {
                            IconButton(onClick = {
                                val navigationViewRecipe: NavigationViewRecipe= NavigationViewRecipe()
                                GoBack(activity2 = navigationViewRecipe)
                            }) {
                                Icon(
                                    asset = vectorResource(id = R.drawable.previous),tint = Color(239, 200, 8))
                               // Icon(,tint = Color(239, 200, 8))

                            }
                            Text(query.toString(),style = MaterialTheme.typography.h5, modifier = Modifier.padding(10.dp),textAlign = TextAlign.Center)
                        }
                    },
                    bodyContent = {
                        LazyColumn {
                            itemsIndexed(items = result) { index, result ->
                                CardRecipee(applicationContext,
                                    recipe = result.recipe,
                                    onClick = {
                                        OpenDetailsRecipe(result.recipe)
                                    })
                            }
                        }
                    })
                /*  Column{
                      TextField(
                              value = state.value,
                              onValueChange = { state.value = it }
                      )
                  }*/


            }
        })




    }

    fun GoBack(activity2:AppCompatActivity) {

        val intent = Intent(applicationContext, activity2::class.java)
        startActivity(intent)
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

    private fun showFirstTodo(){

        var obj: MutableList<hitsobj?>? = null
        viewModel.getFirstTodo().observe(this, Observer {
            obj = it.hit
            for (receta in obj!!) {
                listRecipe.add(receta!!)
                Log.e(TAG, listRecipe.toString())
            }
        })
        Log.e(TAG, "heyyy $listRecipe")
    }

}