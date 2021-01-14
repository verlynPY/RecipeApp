package com.example.testintretrofit.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Utils
import com.example.testintretrofit.view.ui.TestintretrofitTheme

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    lateinit var handler: Handler
    @ExperimentalLazyDsl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this@SplashScreen, MainViewRecipe::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        },1500)
        setContent {
            var imagemodifier = Modifier
                .preferredWidth(200.dp)
                .preferredHeight(200.dp)
          Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                  Image(imageResource(id = R.mipmap.receta), modifier = imagemodifier)
          }
        }


    }
}
