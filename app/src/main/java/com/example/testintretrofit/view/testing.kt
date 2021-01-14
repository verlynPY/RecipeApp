package com.example.testintretrofit.view

import CardRecipee
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.ui.tooling.preview.Preview
import com.example.testintretrofit.model.Utils.DataLoaded
import com.example.testintretrofit.model.hitsobj
import com.example.testintretrofit.view.ViewComponent.CircularProgressBar
import com.example.testintretrofit.view.ui.TestintretrofitTheme
import com.example.testintretrofit.viewmodel.MainViewModel

class testing : AppCompatActivity() {
  //  lateinit var viewModel: MainViewModel
    private var listRecipe:ArrayList<hitsobj> = ArrayList()
    private var dataisloading:Boolean = true
    private val viewModel: MainViewModel by viewModels()
    @ExperimentalLazyDsl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var obj: MutableList<hitsobj?>? = null

      //  var recipe = viewModel.recipe

        setContent {
            Scaffold(
                bottomBar = {
                    BottomAppBar(backgroundColor = Color(0, 0, 0)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(
                                modifier = Modifier.absolutePadding(
                                    10.dp,
                                    0.dp,
                                    10.dp,
                                    0.dp
                                ), onClick = {

                                }) {
                                Icon(Icons.Filled.Favorite, tint = Color(239, 200, 8))
                            }
                            IconButton(
                                modifier = Modifier.absolutePadding(
                                    10.dp,
                                    0.dp,
                                    10.dp,
                                    0.dp
                                ), onClick = {
                                    finish()
                                    overridePendingTransition(0, 0)
                                    startActivity(intent)
                                    overridePendingTransition(0, 0)

                                }) {
                                Icon(Icons.Filled.Home, tint = Color(239, 200, 8))

                            }
                            IconButton(
                                modifier = Modifier.absolutePadding(
                                    10.dp,
                                    0.dp,
                                    10.dp,
                                    0.dp
                                ), onClick = {

                                }) {
                                Icon(Icons.Filled.Search, tint = Color(239, 200, 8))
                            }
                        }
                    }
                },
                bodyContent = {

                    /*
                        obj = recipe!!.hit
                    for (receta in obj!!) {
                        listRecipe.add(receta!!)
                        dataisloading = false
                    }
                    val result = listRecipe
                        LazyColumn {
                            itemsIndexed(items = result) { index, result ->
                                CardRecipee(applicationContext, result.recipe, onClick = { })
                            }
                        }

*/

                    CircularProgressBar(Enable = false)

                })
        }
        }


}
