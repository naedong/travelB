package kr.tr.home.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import coil.compose.rememberAsyncImagePainter
import kr.tr.commom.R
import kr.tr.commom.theme.CustomMaterialTheme

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오전 10:18
 */
@Composable
fun SchedulePageItem(
    imagePath: String?,
    usageAmount: String?,
    usageDayWeekAndTime: String?,
    gugunNm: String?,
    mainPlace: String?,
    place: String?,
    subTitle: String?,
    usageDay: String?,
    nav: NavHostController,
    ) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = 40.dp,
            vertical = 6.dp
        )
        .clip(RoundedCornerShape(6.dp))
        .border(
            color = Color.Black,
            width = 1.dp,
            shape = RoundedCornerShape(6.dp)
        )
        .background(Color.White),

    ){
        Row(
            Modifier
                .padding(all = 3.dp)
                .clickable {
                    nav.navigate("mainHome/detail")
                }
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,

        ){

            imagePath?.let { item ->
                Image(painter =
                rememberAsyncImagePainter(item),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(6.dp)
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentDescription = "")

            }

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    )
                    .width(120.dp)
                    .height(100.dp)
            ) {
                mainPlace?.let { item ->

                    if(item == ""){
                        place?.let { subItem ->
                            if(subItem == ""){
                                subTitle?.let { titleItem ->
                                    Text(
                                        text = titleItem,
                                        fontSize = 12.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        letterSpacing = 0.6.sp,

                                        fontFamily = CustomMaterialTheme
                                            .typography
                                            .maruBuri_Bold,
                                    )
                                }
                            }
                            else {
                                Text(
                                    text = subItem,
                                    fontSize = 12.sp,
                                    maxLines = 1,
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
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            letterSpacing = 0.6.sp,

                            fontFamily = CustomMaterialTheme
                                .typography
                                .maruBuri_Bold,
                        )
                    }
                }

                
                Spacer(modifier = Modifier.height(2.dp))

                usageAmount?.let { item ->
                    if (item == "") return
                    Text(
                        text = item,
                        fontSize = 7.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 0.6.sp,
                        fontFamily = CustomMaterialTheme
                            .typography
                            .maruBuri_Light,
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))


                usageDayWeekAndTime?.let { item ->
                    if (item == ""){
                     usageDay?.let { dayItem ->
                         Text(
                             text = dayItem,
                             fontSize = 8.sp,
                             maxLines = 1,
                             overflow = TextOverflow.Ellipsis,
                             letterSpacing = 0.6.sp,
                             fontFamily = CustomMaterialTheme
                                 .typography
                                 .maruBuri_Light,
                         )

                     }
                    }
                    else {
                        Text(
                            text = item,
                            fontSize = 8.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            letterSpacing = 0.6.sp,
                            fontFamily = CustomMaterialTheme
                                .typography
                                .maruBuri_Light,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                gugunNm?.let { item ->
                    if (item == "") return
                    Text(
                        text = item,
                        fontSize = 8.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        letterSpacing = 0.6.sp,
                        fontFamily = CustomMaterialTheme
                            .typography
                            .maruBuri_Light,
                    )
                }

            }
            Icon(
                ImageVector.vectorResource(
                    id = R.drawable.baseline_chevron_right_24),
                contentDescription = "()",
                modifier = Modifier
            )
        }
    }
}

