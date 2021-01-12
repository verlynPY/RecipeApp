package com.example.testintretrofit.view

import CardRecipee
import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Recipe
import com.example.testintretrofit.viewmodel.MainViewModel

@ExperimentalLazyDsl
class FavoriteViewRecipe : AppCompatActivity() {
    lateinit var viewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
       var data: ArrayList<Recipe>? = ArrayList()
        viewmodel.GetFavorite().observe(this, Observer {
            data!!.add(it)

            setContent {
                Scaffold(   topBar = {
                    TopAppBar(backgroundColor = Color(0, 0, 0)) {
                        IconButton(onClick = {
                            finish()
                        }) {
                            Icon(
                                    asset = vectorResource(id = R.drawable.previous),tint = Color(239, 200, 8))
                        }
                    }
                },bodyContent = {
                     LazyColumn {
                         itemsIndexed(items = data) { index, data ->

                   CardRecipee(context = applicationContext, recipe = data, onClick = {OpenDetailsRecipe(data)})

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
}
