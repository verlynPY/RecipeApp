package com.example.testintretrofit.model

data class Recipe(

        var uri:String? = null,
        var label:String? = null,
        var image:String? = null,
        var source:String? = null,
        var url: String? = null,
        var yield:Int = 0,
        var calories:Float = 0f,
        var ingredientLines: ArrayList<String>
   //  var totalNutrients:String? = null,
   //  var totalDaily:String? = null
    // private var dietLabels:Enum<E>?
    // private var healthLabels:Enum<E>?








)