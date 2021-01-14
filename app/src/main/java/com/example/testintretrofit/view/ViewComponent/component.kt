import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Favorite.FavoriteHelper
import com.example.testintretrofit.model.Recipe
import com.example.testintretrofit.model.Utils
import com.example.testintretrofit.view.FavoriteViewRecipe
import com.google.firebase.FirebaseApp
import retrofit2.http.Url


     val imagemodifier = Modifier
        .preferredHeight(200.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(15.dp))
    @Composable
    fun CardRecipee(Favorite_Enable:Boolean,context: Context,recipe: Recipe, onClick: () -> Unit) {
        Card(
            shape = RoundedCornerShape(size = 10.dp),
            backgroundColor = Color.Black,
            modifier = Modifier.padding(5.dp)
                .clickable(onClick = onClick)
        )

        {
            var bitmap by remember { mutableStateOf<Bitmap?>(null)}
            Glide.with(ContextAmbient.current).asBitmap()
                    .load(recipe.image)
                    .placeholder(R.drawable.img_error)
                    .error(R.drawable.img_error)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        bitmap = resource
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                     //  bitmap = placeholder
                    }
                })
                        Column(modifier = Modifier.padding(5.dp)) {
                            if (bitmap != null ){
                        Image(bitmap!!.asImageAsset(),
                        modifier = imagemodifier,
                        contentScale = ContentScale.Crop)
                            }
                            else{
                                Image(imageResource(R.drawable.img_error),
                                        modifier = imagemodifier,
                                        contentScale = ContentScale.Crop)
                            }
                Spacer(Modifier.preferredHeight(5.dp))
                Text(recipe.label.toString(), style = MaterialTheme.typography.h6, color = Color(174,174,174))
               SettingButton(Favorite_Enable,context = context,recipe = recipe)
            }
        }
    }
    @Composable
    fun SettingButton(Favorite_Enable: Boolean,context: Context, recipe: Recipe){
        if(Favorite_Enable){
            IconButton(onClick = {
                val favoriteHelper: FavoriteHelper = FavoriteHelper()
                favoriteHelper.Delete(context, recipe.label.toString())
            }) {
                Icon(
                        asset = vectorResource(id = R.drawable.favorite_full),
                        tint = Color(239, 200, 8)
                )
            }
        }
        else{
            IconButton(onClick = {
                val favoriteHelper: FavoriteHelper = FavoriteHelper()
                favoriteHelper.SaveFavorite(context = context, recipe = recipe)
            }) {
                Icon(
                        asset = vectorResource(id = R.drawable.favorite_empty),
                        tint = Color(239, 200, 8)
                )
            }
        }

    }
    @Composable
    fun LoadImage(UrlImage:String){
        val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)
        var bitmap by remember { mutableStateOf<Bitmap?>(null)}
        Glide.with(ContextAmbient.current).asBitmap()
            .load(UrlImage)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
        if (bitmap != null )
            Image(bitmap!!.asImageAsset(), Modifier.fillMaxWidth())
      //  return bitmapState
    }
@ExperimentalLazyDsl
@Composable
fun NavigationRecipe(context: Context){



    val imagemodifier = Modifier
        .preferredHeight(150.dp)
        .preferredWidth(180.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .absolutePadding(8.dp,0.dp,8.dp,0.dp)
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Search", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 42.sp),
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally).absolutePadding(0.dp,10.dp,0.dp,5.dp))
        val password = remember { mutableStateOf("") }
        TextField(
                backgroundColor = Color(82, 82, 82),
                modifier = Modifier.fillMaxWidth(.9f),
                leadingIcon = { Icon(Icons.Filled.Search, tint = Color(239, 200, 8)) },
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Waffles",style = TextStyle( fontSize = 16.sp)) },
                imeAction = ImeAction.Done,
                onImeActionPerformed = { action, softKeyboardController ->
                    if (action == ImeAction.Done) {
                        Utils.SendQuery(context = context, password.value)
                        softKeyboardController?.hideSoftwareKeyboard()
                    }
                }
        )
        Row(  modifier = Modifier.fillMaxWidth(.9f)){
            Text(text = "Categories", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                    modifier = Modifier.absolutePadding(0.dp,20.dp,0.dp,0.dp))
        }

        Row{
            Image(imageResource(id = R.drawable.meatedt), modifier = imagemodifier.clickable(onClick = {
                Utils.SendQuery(context = context,"meat")
            }))
            Image(imageResource(id = R.drawable.breadedt), modifier = imagemodifier.clickable(onClick = {
                Utils.SendQuery(context = context,"bread")
            }))
        }
        Row{
            Image(imageResource(id = R.drawable.vegetableedt), modifier = imagemodifier.clickable(onClick = {
                Utils.SendQuery(context = context,"vegetable")
            }))
            Image(imageResource(id = R.drawable.ice_creamedt), modifier = imagemodifier.clickable(onClick = {
                Utils.SendQuery(context = context,"icecream")
            }))
        }
        Row{
            Image(imageResource(id = R.drawable.wafflesedt), modifier = imagemodifier.clickable(onClick = {
                Utils.SendQuery(context = context,"waffles")
            }))
            Image(imageResource(id = R.drawable.cakeedt), modifier = imagemodifier.clickable(onClick = {
                Utils.SendQuery(context,"cake")
            }))
        }
    }
}
var imagemodifierdetails = Modifier
        .preferredHeight(260.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(15.dp))
@Composable
fun ShowRecipeDetails(Ingredientes:ArrayList<String>,Label:String,UrlImage:String){
    var bitmap by remember { mutableStateOf<Bitmap?>(null)}

    Glide.with(ContextAmbient.current).asBitmap()
            .load(UrlImage)
            .error(R.drawable.img_error)
            .placeholder(R.drawable.img_error)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    Column(modifier = Modifier.padding(5.dp)) {
        if (bitmap != null ) {
            Image(
                    bitmap!!.asImageAsset(),
                    modifier = imagemodifierdetails,
                    contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.preferredHeight(12.dp))
        Text(Label, style = MaterialTheme.typography.h4,color = Color(239, 200, 8) ,textAlign = TextAlign.Center, modifier = Modifier.padding(5.dp))
        Spacer(Modifier.preferredHeight(8.dp))
        for (item in Ingredientes) {
            Text(
                    item,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(10.dp),
                    color = Color(159,128,0)
            )
        }
        Spacer(Modifier.preferredHeight(45.dp))
    }

}


