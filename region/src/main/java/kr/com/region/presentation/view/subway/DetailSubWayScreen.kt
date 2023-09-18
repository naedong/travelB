package kr.com.region.presentation.view.subway

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import kr.com.region.item.LocationCardItem
import kr.com.region.presentation.model.SubWayViewModel
import kr.tr.commom.items.KakaoMapURL
import kr.tr.domain.model.item.SubWayItemData
import kr.tr.commom.R
import kr.tr.commom.theme.CustomMaterialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSubWayScreen(navController: NavHostController, subWayItemData: SubWayItemData) {
    val viewModel = hiltViewModel<SubWayViewModel>()
    val viewModelItem = viewModel.getSubWayMapLocation(
        mapX = subWayItemData.longitude ?: "",
        mapY = subWayItemData.latitude ?: "",
        radius = "20000",
        contentTypeID = "39"
    ).collectAsLazyPagingItems()

    val setContentTypeId = rememberSaveable {
        mutableStateOf("")
    }




    Scaffold(
        topBar = {
            TopAppBar(
                    title = { Text(text =
                        stringResource(id = R.string.Back_Handler_Text),
                    modifier = Modifier,
                    fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp, 48.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                },
                modifier = Modifier.
                drawBehind {
                    val borderSize = 3.dp.toPx()
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = borderSize
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(
                     it
                        ),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                Card(
                    border = BorderStroke(3.dp, Color.Black),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.3f),
                ) {
                    smallMapView(subWayItemData.stationName, subWayItemData.longitude, subWayItemData.latitude)
                }

            Text(text = "주변 가게 목록")

                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .border(
                            1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                    )
                {

                    items(viewModelItem){ items ->
                        LocationCardItem(
                            items?.title ?: "",
                            items?.firstimage ?: "",
                            items?.firstimage2 ?: "",
                            items?.tel ?: "",
                            items?.sigungucode ?: ""
                        )
                    }
                }
                Text(text = "$subWayItemData")

            }

        }
    )
}

@Composable
fun smallMapView(stationName: String?, longitude: String?, latitude: String?) {
    val webViewState = rememberWebViewState(url = "${KakaoMapURL().webViewPrefix}${stationName ?: ""},${latitude ?: "35.508"},${longitude ?: "127.580"}")

    val webViewClient = AccompanistWebViewClient()
    val webViewChromeClient = AccompanistWebChromeClient()

    WebView(state = webViewState,
        client = webViewClient,
        chromeClient = webViewChromeClient,
        onCreated = {webView ->
            with(webView){
                settings.run {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        javaScriptCanOpenWindowsAutomatically = false
                }
            }
        }
    )
}


