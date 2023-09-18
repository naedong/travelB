package kr.tr.home.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kr.tr.commom.items.NavigationItem
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.commom.utill.TypeConvetor
import kr.tr.domain.model.item.GetFestivalKrItem

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오전 1:35
 */

@Composable
fun CardItem(
    classPath: GetFestivalKrItem,
    nav: NavHostController,
) {

    Card(

        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier
            .padding(20.dp, 5.dp, 20.dp, 10.dp),
        //set card elevation of the card
        border = BorderStroke(2.dp, Color.Black),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD5E3EE),
        ),
    ) {
        Column(modifier = Modifier.clickable {

            nav.navigate(
                NavigationItem.mainHome.route +"/Schedule" + "/detail?ucSeq=${
                    TypeConvetor(clazz = GetFestivalKrItem::class.java).ClassToJsonString(
                        classPath
                    )
                }" )
        }
        ) {
            classPath.mainImgNormal?.let {

                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                )

            }

            Column(

                modifier = Modifier
                    .padding(
                        11.dp
                    )
                    .fillMaxWidth(0.9f)
            ) {
                classPath.title?.let { item ->

                    if (item == "") {
                        classPath.place?.let { placeItem ->
                            if (placeItem == "") {
                                classPath.subtitle?.let { subTitleItem ->
                                    Text(
                                        text = subTitleItem,
                                        fontSize = 15.sp,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        letterSpacing = 0.6.sp,
                                        fontFamily = CustomMaterialTheme
                                            .typography
                                            .maruBuri_Bold,
                                    )
                                }
                            } else {
                                Text(
                                    text = placeItem,
                                    fontSize = 15.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    letterSpacing = 0.6.sp,
                                    fontFamily = CustomMaterialTheme
                                        .typography
                                        .maruBuri_Bold,
                                )
                            }
                        }
                    } else {
                        Text(
                            text = item,
                            fontSize = 15.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            letterSpacing = 0.6.sp,
                            fontFamily = CustomMaterialTheme
                                .typography
                                .maruBuri_Bold,
                        )
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))

                classPath.gugunNm?.let { item ->

                    Text(
                        text = item,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = CustomMaterialTheme
                            .typography
                            .maruBuri_Light,
                    )
                }
            }
        }
    }
}