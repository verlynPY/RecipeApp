package com.example.testintretrofit.model.Favorite

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class FavoriteHelper {

    val db = FirebaseFirestore.getInstance()

    fun SaveFavorite(context: Context, Uri:String){

      //  FirebaseApp.initializeApp(context)
      //  val db = Firebase.firestore

        val Id_User:String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val favorito = DataFavorite(IdUser = Id_User, Uri_Recipe = Uri)
        db.collection("favoritos").document(Id_User).set(favorito)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(context, "Receta guardada con exito", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener { e->
                    Toast.makeText(context, "Fallo al guardar la receta $e", Toast.LENGTH_SHORT).show()
                }
    }

    fun ReadFavorite(): MutableLiveData<DataFavorite> {

        val liveData = MutableLiveData<DataFavorite>()
        db.collection("favoritos")
                .get()
                .addOnCompleteListener {task->
                    for(document in task.result!!) {
                        liveData.value = document.toObject()
                    }
                }
        return liveData
    }

}