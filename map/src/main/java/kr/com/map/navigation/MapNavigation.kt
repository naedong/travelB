package kr.com.map.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kr.com.map.presentation.main.MapRouter
import kr.tr.commom.items.NavigationItem

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 1:15
 */
fun NavGraphBuilder.mapScreen(navController: NavHostController) {
    composable(
        route = NavigationItem.location.route,
    ) {
        MapRouter(navController)
    }
}
