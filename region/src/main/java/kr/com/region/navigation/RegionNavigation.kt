package kr.com.region.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kr.com.region.presentation.view.RegionRouter
import kr.tr.commom.items.NavigationItem

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오후 2:45
 */
fun NavGraphBuilder.regionScreen(
    navController: NavHostController,
) {
    composable(NavigationItem.region.route){
        RegionRouter(navController)
    }




}