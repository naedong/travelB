package kr.com.region.presentation.view.region


import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.com.region.presentation.model.ListDataModel
import kr.com.region.util.LoadAndDisplayVectorImage

import kr.tr.commom.items.NavigationItem
import kr.tr.commom.items.RegionInfoCard
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.domain.model.item.AreaBasedListItem
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

/**
 * HomeSchedulePage.kt
 * Created by Naedong
 * Date: 2023-08-22
 * Time: 오전 10:49
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRegionPage(navController: NavHostController, code: String?) {
    val viewModel = hiltViewModel<ListDataModel>()

    val views: State<AreaBasedListItem?> =
        viewModel.getAreaBasedListItem.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = CustomMaterialTheme.colorScheme.mySchemeOnPrimary.copy(0.0f),
                    containerColor = Color.Black.copy(0.2f),
                ),
                title = {
                    views.value?.title?.let {
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
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,

                        modifier = Modifier
                            .size(48.dp, 48.dp)
                            .clickable {
                                navController.popBackStack()
//                                            navController.navigate(NavigationItem.region.route) {
//                                                popUpTo(navController.graph.id) {
//                                                    inclusive = true
//                                                }
//                                            }
                            },
                        tint =
                        Color.White,
                        //CustomMaterialTheme.colorScheme.mySchemePrimary,
                    )
                }
            )
        },
    ) {
        RegionDetilView(views.value, it, code)
    }

}

@Composable
fun RegionDetilView(value: AreaBasedListItem?, paddingValues: PaddingValues, code: String?) {

    LazyColumn {
        item(value) {

            Box {
                Column(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    value?.firstimage?.let {
                        LoadAndDisplayVectorImage(it)
                    } ?: value?.firstimage2?.let {
                        LoadAndDisplayVectorImage(it)
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    RegionInfoCard(
                        name = value?.title ?: "",
                        number = value?.tel ?: "",
                        location = value?.addr1 ?: "" + value?.addr2 ?: ""
                    )



                }
            }
        }
    }
}

