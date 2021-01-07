package com.example.testintretrofit.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.testintretrofit.R
import com.example.testintretrofit.view.ui.TestintretrofitTheme

class DetailsViewRecipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val label = bundle!!.getCharSequence("Label")
        val Image = bundle!!.getCharSequence("Image")
        val Ingredientes:ArrayList<String> = bundle!!.getStringArrayList("Ingredientes") as ArrayList<String>
        setContent {
            val fabShape = CircleShape
            val scaffoldState = rememberScaffoldState()



            Scaffold(   topBar = {
                TopAppBar(backgroundColor = Color(0, 0, 0)) {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            asset = vectorResource(id = R.drawable.previous),tint = Color(239, 200, 8))
                        // Icon(,tint = Color(239, 200, 8))

                    }


                }
            },

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
                }, bodyContent = {
                ScrollableColumn(Modifier.fillMaxSize()) {
                    ShowRecipeDetails(Ingredientes,label.toString(),Image.toString())}
            })



        }
    }
}

var imagemodifier = Modifier
    .preferredHeight(240.dp)
    .fillMaxWidth()
    .clip(shape = RoundedCornerShape(10.dp))
var imagedefault = R.drawable.carbs


@Composable
fun ShowRecipeDetails(Ingredientes:ArrayList<String>,Label:String,UrlImage:String){
    var bitmap by remember { mutableStateOf<Bitmap?>(null)}

    Glide.with(ContextAmbient.current).asBitmap()
        .load(UrlImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    Column(modifier = Modifier.padding(5.dp)) {
        if (bitmap != null ) {
            Image(
                bitmap!!.asImageAsset(),
                modifier = imagemodifier,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.preferredHeight(12.dp))
        Text(Label, style = MaterialTheme.typography.h4, textAlign = TextAlign.Center, modifier = Modifier.padding(5.dp))
        Spacer(Modifier.preferredHeight(8.dp))
       // Surface(color = Color.Yellow, modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            for(item in Ingredientes){
                Text(item, style = MaterialTheme.typography.h6, modifier = Modifier.padding(10.dp))
            }

      //  }
    }

}

