package kr.tr.commom.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kr.tr.commom.theme.CustomMaterialTheme

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-27
 * Time: 오후 2:40
 */

@Composable
fun GridItem(posterPath: String, title: String, desc: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = CustomMaterialTheme.colorScheme.mySchemeOnPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ImageContainer(posterPath = posterPath)

            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 2
            )

            if (desc.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = desc,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(Modifier.padding(8.dp))
        }
    }
}



@Composable
fun ImageContainer(posterPath: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(8.dp),
            painter = rememberAsyncImagePainter(
                model = posterPath
            ),
            contentScale = ContentScale.Fit,
            contentDescription = ""
        )
    }
}