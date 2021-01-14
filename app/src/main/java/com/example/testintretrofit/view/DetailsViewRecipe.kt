package com.example.testintretrofit.view


import ShowRecipeDetails
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
            Scaffold(topBar = {
                TopAppBar(backgroundColor = Color(0, 0, 0)) {
                    IconButton(onClick = {
                        finish()
                    }) {
                        Icon(
                            asset = vectorResource(id = R.drawable.previous),tint = Color(239, 200, 8))
                    }
                }
            },bodyContent = {
                ScrollableColumn(Modifier.fillMaxSize()) {
                    ShowRecipeDetails(Ingredientes, label.toString(), Image.toString())
                }
            })
            }
        }
    }

