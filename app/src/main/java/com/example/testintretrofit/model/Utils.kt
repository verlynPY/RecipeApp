package com.example.testintretrofit.model

import CardRecipee
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.Toast
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
import com.example.testintretrofit.view.DetailsViewRecipe
import com.example.testintretrofit.view.FavoriteViewRecipe
import com.example.testintretrofit.view.NavigationViewRecipe
import com.example.testintretrofit.view.SearchViewRecipe
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


object Utils {


    var DataLoaded:Boolean = false

    @ExperimentalLazyDsl
    fun SendQuery(context: Context, query: String){
        val intent = Intent(context, SearchViewRecipe::class.java)
        val bundle = Bundle()
        bundle.putString("Query",query)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

    }

    fun Connection(context: Context):Boolean{
         var Connection_On: Boolean = false
      val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(
                builder.build(),
                object  : ConnectivityManager.NetworkCallback(){
                    override fun onAvailable(network: Network) {
                        Connection_On = true
                        Toast.makeText(context, "Enable", Toast.LENGTH_SHORT).show()
                    }

                    override fun onLost(network: Network) {
                        Connection_On = false
                        Toast.makeText(context, "Disable", Toast.LENGTH_SHORT).show()
                    }
                }

        )
        return Connection_On!!
    }

    fun SearchRecipeBrowser(recipe:String){
        //val intent = Intent(Intent.ACTION_VIEW, Uri.parse())
    }

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
                }
            },
            bodyContent = {
                LazyColumn {
                    itemsIndexed(items = result) { index, result ->
                       // result.recipe.label?.let { it1 -> result.recipe.image?.let { it2 -> CardRecipee(it1, it2, onClick = {}) } }

                    }
                }
            })
    }

    fun GoBack(context: Context,activity2:AppCompatActivity) {
        val intent = Intent(context, activity2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun OpenDetailsRecipe(context: Context, recipe: Recipe){
        val intent = Intent(context, DetailsViewRecipe::class.java)
        val bundle = Bundle()
        bundle.putString("Label",recipe.label)
        bundle.putStringArrayList("Ingredientes", recipe.ingredientLines)
        bundle.putString("Image",recipe.image)
        bundle.putFloat("Calorias", recipe.calories)
        intent.putExtras(bundle)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

    }

    fun OpenNavigation(context: Context, activity2:AppCompatActivity){
        val intent = Intent(context, activity2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
    fun OpenFavorite(context: Context, activity2:AppCompatActivity){
        val intent = Intent(context, activity2::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

    }
}