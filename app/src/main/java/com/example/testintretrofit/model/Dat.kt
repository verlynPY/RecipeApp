package com.example.testintretrofit.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Dat {


    fun getTodo(): MutableLiveData<RecipeObj> {
        val liveData = MutableLiveData<RecipeObj>()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Credentials.Url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        val client: Webservice = retrofit.create<Webservice>(Webservice::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            client.getTodo("n", Credentials.App_Id, Credentials.App_Key).enqueue(object : Callback<RecipeObj> {
                override fun onResponse(call: Call<RecipeObj>, response: Response<RecipeObj>) {
                    if (response.isSuccessful) {
                        liveData.value = response.body()
                    }
                }
                override fun onFailure(call: Call<RecipeObj>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
        return liveData
    }
    fun GetSearchRecipe(query:String): MutableLiveData<RecipeObj> {
        val liveData = MutableLiveData<RecipeObj>()
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Credentials.Url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        val client: Webservice = retrofit.create<Webservice>(Webservice::class.java)

        client.getTodo(query, Credentials.App_Id, Credentials.App_Key).enqueue(object: Callback<RecipeObj> {
            override fun onResponse(call: Call<RecipeObj>, response: Response<RecipeObj>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }
            override fun onFailure(call: Call<RecipeObj>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveData
    }
}