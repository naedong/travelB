package kr.com.plan.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kr.com.plan.presentation.view.planRouter
import kr.tr.commom.items.NavigationItem

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-24
 * Time: 오후 4:13
 */


fun NavGraphBuilder.planScreen(navController: NavHostController) {
    composable(NavigationItem.plan.route){
        planRouter(navController)
    }

}