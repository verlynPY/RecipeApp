package com.example.testintretrofit.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {
    @GET("search")
    fun getTodo(@Query("q") Recipe:String, @Query("app_id") app_id:String, @Query("app_key") app_key:String): Call<RecipeObj>
  }