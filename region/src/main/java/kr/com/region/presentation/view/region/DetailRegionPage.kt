package kr.com.region.presentation.view.region


import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kr.com.region.presentation.model.ListDataModel
import kr.com.region.util.LoadAndDisplayVectorImage

import kr.tr.commom.items.RegionInfoCard
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.domain.model.item.AreaBasedListItem

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
                            },
                        tint =
                        Color.White,
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

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
    ) {
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
                        location = value?.addr1 ?: "" + value?.addr2 ?: "",
                        lng = value?.mapx?.toDouble(),
                        lat =  value?.mapy?.toDouble()
                    )
                }
            }
        }
    }
}



