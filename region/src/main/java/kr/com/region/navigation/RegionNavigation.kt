package kr.com.region.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.com.region.presentation.view.RegionRouter
import kr.com.region.presentation.view.region.DetailRegionPage
import kr.com.region.presentation.view.subway.DetailSubWayScreen
import kr.tr.commom.items.NavigationItem
import kr.tr.commom.utill.TypeConvetor
import kr.tr.domain.model.item.SubWayItemData

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

        RegionRouter(navController, )
    }

    composable(NavigationItem.region.route+"/{rnum}",
        arguments = listOf(
            navArgument(("rnum")){
              type = NavType.StringType
            },
        )
        ){

        DetailRegionPage(
               navController, it.arguments?.getString("rnum")
            )
    }

    composable(NavigationItem.region.route+"/detail/{item}", ){
        val item = it.arguments?.getString("item")
        item?.let { arg ->
            DetailSubWayScreen(navController, TypeConvetor(SubWayItemData::class.java).JsonStringToClass(arg))
        }
    }

}