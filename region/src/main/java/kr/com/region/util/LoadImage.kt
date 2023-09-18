package kr.com.region.util

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-25
 * Time: 오후 2:42
 */

@Composable
fun LoadAndDisplayVectorImage(url : String) {



    AsyncImage(model = url,
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        contentScale = ContentScale.Crop,
        contentDescription = "null")


}
