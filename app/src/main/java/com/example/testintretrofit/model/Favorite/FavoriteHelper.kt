package com.example.testintretrofit.model.Favorite

import android.content.Context
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.testintretrofit.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore


class FavoriteHelper {

    val db = FirebaseFirestore.getInstance()

    fun SaveFavorite(context: Context, recipe: Recipe){
      val Id_User:String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val favorito = recipe
        db.collection("favoritos").document().set(favorito)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(context, "Receta guardada con exito", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e->
                    Toast.makeText(context, "Fallo al guardar la receta $e", Toast.LENGTH_SHORT).show()
                }
    }

    fun ReadFavorite(): MutableLiveData<Recipe> {
        val liveData = MutableLiveData<Recipe>()
        val documentreference = db.collection("favoritos")
        documentreference.get().addOnSuccessListener() {result ->
            for (document in result) {
                var ingredientlist:ArrayList<String> = ArrayList()
              ingredientlist.add("sugar")
                var yield:Int = document.getLong("yield")!!.toInt()
                var recipe = Recipe(document.getString("uri"), document.getString("label"),
                document.getString("image"), document.getString("source"), document.getString("url"),
                document.getLong("yield")!!.toInt(), 5f,
                ingredientlist)
               liveData.value = recipe
            }

        }
        return liveData
    }

}