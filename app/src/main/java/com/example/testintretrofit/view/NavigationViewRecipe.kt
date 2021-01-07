package com.example.testintretrofit.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.testintretrofit.R
import com.example.testintretrofit.view.ui.TestintretrofitTheme
import retrofit2.http.Query

@ExperimentalLazyDsl
class NavigationViewRecipe : AppCompatActivity() {
    @ExperimentalLazyDsl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



            Scaffold(


                topBar = {
                    TopAppBar(backgroundColor = Color(0, 0, 0)) {
                        /*IconButton(onClick = {
                            // scaffoldState.drawerState.open()
                        }) {
                            Icon(Icons.Filled.Search,tint = Color(239, 200, 8))

                        }*/
                        val password = remember { mutableStateOf("") }
                        TextField(modifier = Modifier.fillMaxWidth(),
                            backgroundColor = Color(82, 82, 82),
                            value = password.value,
                            onValueChange = { password.value = it },
                            imeAction = ImeAction.Done,
                            label = { Text("Search") },
                            leadingIcon = { Icon(Icons.Filled.Search, tint = Color(239, 200, 8)) },
                            onImeActionPerformed = { action, softKeyboardController ->
                                if (action == ImeAction.Done) {
                                    SendQuery(password.value)
                                    softKeyboardController?.hideSoftwareKeyboard()
                                }
                            }


                        )
                    }
                },
                bodyContent = {

                    Spacer(Modifier.preferredHeight(5.dp))
                    NavigationRecipe()

                })
        }
    }

    fun SendQuery(query: String){
        val intent = Intent(applicationContext, SearchViewRecipe::class.java)
        val bundle = Bundle()
        bundle.putString("Query",query)
        intent.putExtras(bundle)

        startActivity(intent)
    }


    @Composable
    fun NavigationRecipe(){
        val imagemodifier = Modifier
                .preferredHeight(140.dp)
                .preferredWidth(170.dp)
                .clip(shape = RoundedCornerShape(10.dp))



        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row{

                Image(imageResource(id = R.drawable.meatedt), modifier = imagemodifier.clickable(onClick = {
                    SendQuery("meat")
                }))
                Image(imageResource(id = R.drawable.breadedt), modifier = imagemodifier.clickable(onClick = {
                    SendQuery("bread")
                }))
            }
            Row{
                Image(imageResource(id = R.drawable.vegetableedt), modifier = imagemodifier.clickable(onClick = {
                    SendQuery("vegetable")
                }))
                Image(imageResource(id = R.drawable.ice_creamedt), modifier = imagemodifier.clickable(onClick = {
                    SendQuery("icecream")
                }))
            }
            Row{
                Image(imageResource(id = R.drawable.wafflesedt), modifier = imagemodifier.clickable(onClick = {
                    SendQuery("waffles")
                }))
                Image(imageResource(id = R.drawable.cakeedt), modifier = imagemodifier.clickable(onClick = {
                    SendQuery("cake")
                }))
            }
        }
    }
}
