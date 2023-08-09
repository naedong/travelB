package kr.tr.travelbproject.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kr.com.map.view.MapRouter
import kr.tr.commom.items.NavigationItem

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 4:09
 */
fun NavGraphBuilder.mainScreen(navController: NavHostController, function: () -> Unit) {
    composable(
        route = NavigationItem.home.route,
    ) {
        MapRouter(navController)
    }
}
