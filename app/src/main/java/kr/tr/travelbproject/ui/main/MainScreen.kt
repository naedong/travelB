package kr.tr.travelbproject.ui.main

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import  androidx.compose.material.Scaffold

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.FabPosition
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kr.com.map.navigation.mapScreen
import kr.com.plan.navigation.planScreen
import kr.com.region.navigation.regionScreen
import kr.tr.commom.items.NavigationItem
import kr.tr.commom.R
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.home.navigation.homeScreen
import kr.tr.travelbproject.navigation.mainScreen
import kr.tr.travelbproject.ui.viewmodel.ChangeIcon
import me.nikhilchaudhari.library.NeuInsets
import me.nikhilchaudhari.library.neumorphic
import me.nikhilchaudhari.library.shapes.Punched


/**
 * TravelBAppProject
 * Created by Naedong
 * Date: 2023-06-13
 * Time: 오전 11:30
 */

@Preview
@Composable
fun MainScreen() {
    SettingUpBottomNavigationBarAndCollapsing()
}


@Composable
fun ObservedHomeIcon() {

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SettingUpBottomNavigationBarAndCollapsing() {
    val snackbarHostState = remember { SnackbarHostState() }

    val navController = rememberNavController()
    val navBackStackEntry: NavBackStackEntry? = navController.currentBackStackEntryAsState().value
    val indexNavigation = rememberSaveable {
        mutableStateOf(0)
    }

    val currentItemId: String? = navBackStackEntry?.destination?.route


    currentItemId?.let {
        if (it.contains("mainHome")) {
            indexNavigation.value = 0
        } else {
            indexNavigation.value = 1
        }
    }





    Scaffold(modifier = Modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },

        floatingActionButtonPosition = FabPosition.Center,

        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }, modifier = Modifier

                    .padding(6.dp)
                    .neumorphic(
                        lightShadowColor = Color.White,
                        darkShadowColor = Color.DarkGray,
                        elevation = 9.dp,
                        strokeWidth = 3.dp,
                        neuInsets = NeuInsets(0.dp, 1.dp),
                        neuShape = Punched.Oval()

                    ), backgroundColor = Color.Black
            ) {
                AnimatedContent(targetState = indexNavigation.value,
                    transitionSpec = {
                        if (targetState > initialState) {
                            // If the target number is larger, it slides up and fades in
                            // while the initial (smaller) number slides up and fades out.
                            scaleIn() with scaleOut()
               //             slideInVertically { height -> height } + fadeIn() with
                 //                   slideOutVertically { height -> -height } + fadeOut()
                        } else {
                            // If the target number is smaller, it slides down and fades in
                            // while the initial number slides down and fades out.
                            scaleIn() with scaleOut()

//                            slideInVertically { height -> -height } + fadeIn() with
//                                    slideOutVertically { height -> height } + fadeOut()
                        }.using(
                            // Disable clipping since the faded slide-in/out should
                            // be displayed out of bounds.
                            SizeTransform(clip = false)
                        )
                    }

                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id =
                            NavigationItem.Companion.bottomNavigationItems
                                .get(it)
                                .icon
                        ),
                        contentDescription = "Home", modifier = Modifier
                            .clickable {
                                navController.navigate(NavigationItem.mainHome.route + "/1") {
                                    popUpTo(NavigationItem.mainHome.route + "/1") {
                                        inclusive = true
                                    }
                                }
                            }
                            .size(80.dp)
                            .background(Color.White)
                    )
                }

            }
        },
        isFloatingActionButtonDocked = true,

        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.fillMaxHeight(0.1f), navController
            )
        }) {

        MainScreenNavigationConfigurations(navController, it)


    }
}


@Composable
fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {

    NavHost(
        navController,
        startDestination = NavigationItem.mainHome.route + "/1",
    ) {


        mainScreen(navController)
        homeScreen(navController)
        mapScreen(navController)
        regionScreen(navController)
        planScreen(navController)
    }
}


@Composable
fun BottomNavigationBar(modifier: Modifier, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomAppBar(
        backgroundColor = colorResource(id = R.color.white),
        modifier = modifier,
        cutoutShape = CircleShape
    ) {

        Row(
            modifier = Modifier
                .fillMaxHeight()

        ) {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxHeight()
                    .graphicsLayer { clip = true },
                backgroundColor = Color.White
            ) {
                NavigationItem.Companion.bottomNavigationItems.forEachIndexed { index, item ->
                    if (index in 2..5) {

                        BottomNavigationItem(icon = {
                            Icon(
                                ImageVector.vectorResource(
                                    id = item.icon,
                                ), contentDescription = item.route,
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .fillMaxWidth(0.5f)
                                    .fillMaxHeight(0.5f)
                            )
                        },
                            label = {
                                Text(
                                    text = item.title,
                                    fontFamily = CustomMaterialTheme.typography.thejamsiloftlight,

                                    )
                            },
                            selectedContentColor = Color.Black,
                            unselectedContentColor = Color.Black.copy(0.4f),
                            alwaysShowLabel = true,
                            modifier = Modifier
                                .background(Color.White)
                                .padding(5.dp),
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }

                            })

                        if (index == 3) {
                            Box(
                                modifier = modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.2f)
                            )
                        }

                    }
                }
            }
        }

    }

}


