package com.example.testintretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testintretrofit.model.RecipeObj
import com.example.testintretrofit.model.Dat

class MainViewModel : ViewModel() {
    val repository: Dat = Dat()


    fun getFirstTodo(): MutableLiveData<RecipeObj> {

        return repository.getTodo()
    }

    fun GetSearchRecipe(query:String): MutableLiveData<RecipeObj> {
        return repository.GetSearchRecipe(query)
    }
}