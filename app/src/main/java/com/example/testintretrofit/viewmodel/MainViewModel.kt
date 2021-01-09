package com.example.testintretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testintretrofit.model.RecipeObj
import com.example.testintretrofit.model.Dat
import com.example.testintretrofit.model.Favorite.DataFavorite
import com.example.testintretrofit.model.Favorite.FavoriteHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val repository: Dat = Dat()
    val repositorio_favorite: FavoriteHelper = FavoriteHelper()

    fun getFirstTodo(): MutableLiveData<RecipeObj> {

        return repository.getTodo()
    }

    fun GetSearchRecipe(query:String): MutableLiveData<RecipeObj> {
        return repository.GetSearchRecipe(query)
    }

  /*  fun GetFavorite(): MutableLiveData<DataFavorite>{
        return repositorio_favorite.ReadFavorite()
    }
*/
}