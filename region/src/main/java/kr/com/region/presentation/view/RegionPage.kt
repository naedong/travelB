package kr.com.region.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오후 2:47
 */

@Composable
fun RegionRouter(navController: NavHostController) {
    RegionPage(navController, )
}

@Composable
fun RegionPage(navController: NavHostController) {
    RegionScreen(navController,  )
}