package com.example.testintretrofit.model

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
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

        client.getTodo("n", Credentials.App_Id, Credentials.App_Key).enqueue(object: Callback<RecipeObj> {
            override fun onResponse(call: Call<RecipeObj>, response: Response<RecipeObj>) {
                if (response.isSuccessful) {

                    // When data is available, populate LiveData
                    liveData.value = response.body()
                }
            }


            override fun onFailure(call: Call<RecipeObj>, t: Throwable) {
                t.printStackTrace()
            }
        })

        // Synchronously return LiveData
        // Its value will be available onResponse
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