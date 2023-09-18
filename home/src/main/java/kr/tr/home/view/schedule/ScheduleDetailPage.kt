package kr.tr.home.view.schedule

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kr.tr.commom.items.NavigationItem
import kr.tr.commom.items.RegionInfoCard
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.commom.utill.showLongToast
import kr.tr.domain.model.item.GetFestivalKrItem


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오후 3:17
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleDetailPage(navController: NavHostController, index: GetFestivalKrItem?) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    //      titleContentColor = CustomMaterialTheme.colorScheme.mySchemeOnPrimary.copy(0.0f),
                    containerColor = Color.Black.copy(0.2f),
                ),
                title = {
                    index?.title?.let {
                        if (it == "") {
                            index?.subtitle?.let { subTitle ->
                                Text(
                                    text = subTitle,
                                    color =
                                    Color.White,
                                    fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                                    maxLines = 1,
                                    fontSize = 18.sp,
                                )
                            }
                        } else {
                            Text(
                                text = it,
                                color =
                                Color.White,
                                //CustomMaterialTheme.colorScheme.mySchemePrimary,
                                fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                                maxLines = 1,
                                fontSize = 18.sp,
                            )
                        }
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,

                        modifier = Modifier
                            .size(48.dp, 48.dp)
                            .clickable {
                                navController.navigate(NavigationItem.mainHome.route + "/0")
                            },
                        tint =
                        Color.White,
                        //CustomMaterialTheme.colorScheme.mySchemePrimary,
                    )
                }
            )
        },
        content = {
            DetailView(index, it)
        }
    )


}

private fun handleIntent(context: Context, url: String) {
    if (url == "") {
        showLongToast(context, "URL이 존재하지 않습니다.")
        return
    } else {
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        context.startActivity(urlIntent)
    }
}


@Composable
fun DetailView(index: GetFestivalKrItem?, paddingValues: PaddingValues) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        // Basic details
        item {
            index?.mainImgNormal?.let { task ->
                Image(
                    painter =
                    rememberAsyncImagePainter(task),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    alignment = Alignment.CenterStart,
                    contentDescription = ""
                )


                Spacer(modifier = Modifier.height(16.dp))



                when (index?.subtitle) {
                    "" -> when (index?.title) {
                        "" -> when (index?.place) {
                            "" -> null
                            else -> RegionInfoCard(
                                name = index?.place.toString(),
                                number = index?.cntctTel.toString(),
                                location = index?.gugunNm.toString(),
                                lng = index?.lng,
                                lat = index?.lat
                            )
                        }

                        else -> RegionInfoCard(
                            name = index?.title.toString(),
                            number = index?.cntctTel.toString(),
                            location = index?.gugunNm.toString(),
                            lng = index?.lng,
                            lat = index?.lat
                        )
                    }

                    else -> RegionInfoCard(
                        name = index?.subtitle.toString(),
                        number = index?.cntctTel.toString(),
                        location = index?.gugunNm.toString(),
                        lng = index?.lng,
                        lat = index?.lat
                    )
                }
            }
        }

        item {
            index?.itemcntnts?.let {
                Spacer(modifier = Modifier.height(24.dp))
                Title(title = "축제 설명")
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Start
                )
            }
        }
        // Quick info
        item {
            index?.trfcInfo?.let {

                Spacer(modifier = Modifier.height(24.dp))
                Title(
                    title = "오시는 길"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = it)

                }
            }
        }

        // Owner info
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Title(title = "그 외 정보")
            Spacer(modifier = Modifier.height(24.dp))
            index?.usageAmount?.let {
                when (it) {
                    "" -> null
                    else -> {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp, 16.dp, 0.dp),
                            text = "가격 : $it",
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
        item {
            index?.usageDay?.let {
                when (it) {
                    "" -> null
                    else -> {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp, 16.dp, 0.dp),
                            text = "축제 날짜 : $it",
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        item {
            index?.usageDayWeekAndTime?.let {
                when (it) {
                    "" -> null
                    else -> {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp, 16.dp, 0.dp),
                            text = "축제 날짜 : $it",
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }


        item {
            Spacer(modifier = Modifier.height(36.dp))
            index?.homepageUrl?.let {
                Button(
                    onClick = {
                        handleIntent(context, it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Cyan,
                        contentColor = Color.White
                    )
                ) {
                    androidx.compose.material.Text("Home Page")
                }

            }
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .zIndex(0f)
                    .fillMaxWidth()
                    .padding(top = 80.dp, bottom = 20.dp)
                    .background(Color.White.copy(0f))
            )
        }

    }


}

@Composable
fun Title(title: String) {
    androidx.compose.material.Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = Color.Black,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}



