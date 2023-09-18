package kr.com.region.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import kr.tr.commom.R

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-09-18
 * Time: 오전 11:07
 */
@Composable
fun LocationCardItem(title: String, firstImage: String,
                     firstImage2: String,
                     tel: String, sigunguCode: String) {

    Text(text = firstImage)


    Card(
         modifier = Modifier
             .fillMaxWidth()
             .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(16.dp)
            )
    {
        Row {

            AsyncImage(model = firstImage ?: firstImage2,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(15.dp)
                    .width(200.dp)
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),
                contentDescription = "이미지가 없습니다")


            Text(text = "$title")
        }

    }
}