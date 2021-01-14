package com.example.testintretrofit.viewmodel

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testintretrofit.model.RecipeObj
import com.example.testintretrofit.model.Dat
import com.example.testintretrofit.model.Favorite.DataFavorite
import com.example.testintretrofit.model.Favorite.FavoriteHelper
import com.example.testintretrofit.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val repository: Dat = Dat()
    val repositorio_favorite: FavoriteHelper = FavoriteHelper()

    fun getFirstTodo(Lyrics_Random:String): MutableLiveData<RecipeObj> {
        return repository.getTodo(Lyrics_Random)
    }

    fun GetSearchRecipe(query:String): MutableLiveData<RecipeObj> {
        return repository.GetSearchRecipe(query)
    }

    fun GetFavorite(): MutableLiveData<Recipe>{
        return repositorio_favorite.ReadFavorite()
    }

}