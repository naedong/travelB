package kr.tr.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.tr.commom.items.NavigationItem
import kr.tr.commom.utill.TypeConvetor
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.home.view.HomeScreenRoute
import kr.tr.home.view.schedule.ScheduleDetailPage

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-27
 * Time: 오후 11:06
 */
fun NavGraphBuilder.homeScreen(navController: NavHostController) {
    composable(route = NavigationItem.mainHome.route +"/{index}",
        arguments = listOf(
            navArgument("index"){
                type = NavType.StringType
            },
        )
        ) {
        var backStackEntry = it.arguments?.getString("index")

        if (backStackEntry != null) {
            HomeScreenRoute(navController, backStackEntry)
        }else {
            backStackEntry = "1"
           HomeScreenRoute(navController, backStackEntry)
        }
    }


    composable(route = NavigationItem.mainHome.route + "/Schedule" + "/detail?ucSeq={ucSeq}",
    ) {
        val ucSeq = it.arguments?.getString("ucSeq")
        ucSeq?.let {index ->
            val useObject =
                TypeConvetor(GetFestivalKrItem::class.java).JsonStringToClass(index)
            ScheduleDetailPage(navController = navController, useObject)
        }
    }
}
