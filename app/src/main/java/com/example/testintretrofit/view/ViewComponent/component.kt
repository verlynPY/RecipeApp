import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.testintretrofit.R
import com.example.testintretrofit.model.Favorite.FavoriteHelper
import com.google.firebase.FirebaseApp
import retrofit2.http.Url


val imagemodifier = Modifier
        .preferredHeight(200.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(10.dp))


@Composable
    fun CardRecipee(context: Context,label:String,UrlImage: String, onClick: () -> Unit) {

        Card(
            shape = RoundedCornerShape(size = 10.dp),
            backgroundColor = Color.Black,
            modifier = Modifier.padding(5.dp)
                .clickable(onClick = onClick)

        )

        {

            var imagedefault = R.drawable.carbs
            var bitmap by remember { mutableStateOf<Bitmap?>(null)}

            Glide.with(ContextAmbient.current).asBitmap()
                .load(UrlImage)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        bitmap = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })


                        Column(modifier = Modifier.padding(5.dp)) {
                            if (bitmap != null ){
                Image(bitmap!!.asImageAsset(),
                        modifier = imagemodifier,
                        contentScale = ContentScale.Crop
                )
                            }
                Spacer(Modifier.preferredHeight(5.dp))
                Text(label, style = MaterialTheme.typography.h6)
                SettingButton(context = context,Uri = UrlImage)
            }

        }
    }
    @Composable
    fun SettingButton(context: Context, Uri:String){
        //var icon_favorite = R.drawable.favorite_empty
        IconButton(onClick = {
            val favoriteHelper: FavoriteHelper = FavoriteHelper()
            favoriteHelper.SaveFavorite(context = context, Uri = Uri)
        }) {
            Icon(
                    asset = vectorResource(id = R.drawable.favorite_empty),
                    tint = Color(239, 200, 8)
            )
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