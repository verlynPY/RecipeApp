package com.example.testintretrofit.model.Favorite

import android.content.Context
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.testintretrofit.model.Recipe
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class FavoriteHelper {

    val db = FirebaseFirestore.getInstance()
    fun SaveFavorite(context: Context, recipe: Recipe){
        val favorito = recipe
        db.collection("favoritos").document(recipe.label.toString()).set(favorito)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(context, "Recipe successfully saved", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e->
                    Toast.makeText(context, "Failed to saved the recipe $e", Toast.LENGTH_SHORT).show()
                }
    }
    fun ReadFavorite(): MutableLiveData<Recipe> {
        val liveData = MutableLiveData<Recipe>()
        val documentreference = db.collection("favoritos")
        documentreference.get().addOnSuccessListener() { result ->
            for (document in result) {
                var recipe = Recipe(document.getString("uri"), document.getString("label"),
                        document.getString("image"), document.getString("source"), document.getString("url"),
                        document.getLong("yield")!!.toInt(), 5f,
                        document.get("ingredientLines") as ArrayList<String>)
               liveData.value = recipe
            }
        }
        return liveData
    }
    fun Delete(context: Context,Label:String) {
        db.collection("favoritos").document(Label)
                .delete()
                .addOnSuccessListener{
                    Toast.makeText(context, "Recipe eliminated from favorites", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed delete the recipe", Toast.LENGTH_SHORT).show()
                }
    }
}