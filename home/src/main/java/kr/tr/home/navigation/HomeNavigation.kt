package kr.tr.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.tr.commom.items.NavigationItem
import kr.tr.home.view.HomeScreenRoute
import kr.tr.home.view.schedule.ScheduleDetailPage

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-27
 * Time: 오후 11:06
 */
fun NavGraphBuilder.homeScreen(navController: NavHostController) {
    composable(route = NavigationItem.mainHome.route) {
        HomeScreenRoute(navController)
    }

    composable(route = NavigationItem.mainHome.route + "/detail?ucSeq={ucSeq}",
        arguments = listOf(
            navArgument("ucSeq") {
                type = NavType.IntType
            }
        )
    ) {
        val ucSeq = it.arguments?.getInt("ucSeq")

        ucSeq?.let {index ->
            ScheduleDetailPage(navController = navController,index)
        }
    }
}
