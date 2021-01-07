package com.example.testintretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testintretrofit.model.hitsobj
import com.example.testintretrofit.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    val TAG = "TagActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        showFirstTodo()
    }

    private fun showFirstTodo() {
        viewModel.getFirstTodo().observe(this, Observer {
   //         it.hit
            var obj: MutableList<hitsobj?> = it.hit!!
             for(receta in obj!!){
                 Log.i(TAG, receta!!.recipe.label.toString())
             }
          //  Log.e(TAG,"Heyyy ${obj}")


        })
    }
}