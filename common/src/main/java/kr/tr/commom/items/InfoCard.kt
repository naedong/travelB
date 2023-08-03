package kr.tr.commom.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.tr.commom.theme.CustomMaterialTheme

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-02
 * Time: 오후 4:40
 */

@Composable
fun InfoCard(title: String, value: String) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RegionInfoCard(name: String, number: String, location: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = name,
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                    .fillMaxWidth(0.6f),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,

                fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp, 16.dp),
                    tint = Color.Red
                )

                Text(
                    text = location,
                    modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 0.dp),
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

//            Text(
//                text = "12 mins ago",
//                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
//                color = Color.Black.copy(0.9f),
//
//            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            GenderTag(number)
        }
    }
}
@Composable
fun GenderTag(name: String) {
    ChipView(gender = name, colorResource = Color.Cyan)
}

@Composable
fun ChipView(gender: String, colorResource: Color) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource.copy(.06f))
    ) {
        Text(
            text = gender, modifier = Modifier.padding(12.dp, 6.dp, 12.dp, 6.dp),
            fontFamily = CustomMaterialTheme.typography.maruBuri_SemiBold,
            color = colorResource
        )
    }
}