package com.example.testintretrofit.model

import CardRecipee
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat.startActivity


class Utils {
    @ExperimentalLazyDsl
    @Composable
    fun layout(result:ArrayList<hitsobj>){
        Scaffold(

            bottomBar = {
                BottomAppBar(backgroundColor = Color(0,0,0)) {
                    IconButton(onClick = {
                        // scaffoldState.drawerState.open()
                    }) {
                        Icon(Icons.Filled.Favorite,tint = Color(239, 200, 8))

                    }
                    IconButton(onClick = {
                        // scaffoldState.drawerState.open()
                    }) {
                        Icon(Icons.Filled.Home,tint = Color(239, 200, 8))

                    }
                    IconButton(onClick = {
                       //d OpenNavigation()
                    }) {
                        Icon(Icons.Filled.Search,tint = Color(239, 200, 8))

                    }


                    /*  FilledTextField(
                              modifier = Modifier.fillMaxWidth(),
                              backgroundColor = Color(0xffb9b9b9),
                              value = text,
                              onValueChange = { text = it },
                              label = { Text("Email") },
                              leadingIcon = { Icon(Icons.Filled.Favorite) },
                              trailingIcon = { Icon(Icons.Filled.Info) }
                      )
*/
                }
            },
            bodyContent = {
                LazyColumn {
                    itemsIndexed(items = result) { index, result ->
                        result.recipe.label?.let { it1 -> result.recipe.image?.let { it2 -> CardRecipee(it1, it2, onClick = {}) } }

                    }
                }
            })
    }

}