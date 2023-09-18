package kr.com.map.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.com.map.presentation.model.MapViewModel
import kr.tr.commom.R
import kr.tr.commom.theme.CustomMaterialTheme
import net.daum.mf.map.api.MapView

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-30
 * Time: 오전 10:52
 */
@Composable
fun MapTypeCardItem(tagName: String, mapInt: Int, viewModel: MapViewModel, ) {
    Card(
        modifier =
        Modifier
            .width(100.dp)
            .clickable {
                       when(mapInt){
                           R.drawable.default_map -> {  viewModel.setMyMapType(MapView.MapType.Standard)    }
                           R.drawable.hybrid -> {   viewModel.setMyMapType(MapView.MapType.Hybrid)  }
                           else -> {    viewModel.setMyMapType(MapView.MapType.Satellite)   }
                       }
            },
        elevation = CardDefaults.cardElevation(
            6.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painterResource(id = mapInt),
                modifier = Modifier,
                contentDescription = ""
            )
            Text(
                modifier = Modifier,
                text = "$tagName",
                fontFamily = CustomMaterialTheme.typography.maruBuri_SemiBold,
                fontSize = 13.sp,
            )
        }
    }
}