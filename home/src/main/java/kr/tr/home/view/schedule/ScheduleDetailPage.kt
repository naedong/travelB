package kr.tr.home.view.schedule

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import kr.tr.commom.items.RegionInfoCard
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.domain.model.item.GetFestivalKrItem


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오후 3:17
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleDetailPage(navController: NavHostController, index: GetFestivalKrItem? ) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                 //   titleContentColor = CustomMaterialTheme.colorScheme.mySchemeOnPrimary.copy(0.0f),
                    containerColor = Color.Black.copy(0.2f),
                ),
                title = {
                    index?.title?.let {
                        if (it == ""){
                            index?.subtitle?.let{ subTitle ->
                                Text(text = subTitle,
                                    color =
                                    Color.White,
                                    //CustomMaterialTheme.colorScheme.mySchemePrimary,
                                    fontFamily = CustomMaterialTheme.typography.maruBuri_Bold,
                                    maxLines = 1,
                                    fontSize = 18.sp,
                                )
                            }
                        }
                        else{
                            Text(text = it,
                                color =
                                Color.White,
                                //CustomMaterialTheme.colorScheme.mySchemePrimary,
                                fontFamily = CustomMaterialTheme.typography.maruBuri_Bold,
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
                                navController.navigateUp()
                            },
                        tint =
                        Color.White,
                        //CustomMaterialTheme.colorScheme.mySchemePrimary,
                    )
                }
            )
        },
        content = {
            Log.e("Detail Page Padding Values Check", "$it")
            DetailView(index)
        }
    )


}

@Composable
fun DetailView(index : GetFestivalKrItem?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {



        // Basic details
        item {
            index?.mainImgNormal?.let { task ->
                Image(painter =
                rememberAsyncImagePainter(task),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    alignment = Alignment.CenterStart,
                    contentDescription = "")


                Spacer(modifier = Modifier.height(16.dp))
                RegionInfoCard(name = index?.subtitle.toString(), gender = index?.cntctTel.toString(), location = index?.gugunNm.toString())
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
                Title(title = "오시는 길"
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
            index.apply {

                Spacer(modifier = Modifier.height(24.dp))
                Title(title = "Owner info")
                Spacer(modifier = Modifier.height(16.dp))
//                owner.apply {
//                    OwnerCard(name, bio, image)
//                }
            }
        }

        // CTA - Adopt me button
        item {
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                onClick = { /* Do something! */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Cyan,
                    contentColor = Color.White
                )
            ) {
                androidx.compose.material.Text("Adopt me")
            }

            Box(modifier = Modifier
                .height(150.dp)
                .zIndex(0f)
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 20.dp)
                .background(Color.White)
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



