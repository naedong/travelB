package kr.tr.travelbproject.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.tr.commom.items.NavigationItem
import kr.tr.commom.theme.maruBuri_Light
import kr.tr.commom.R
import kr.tr.home.navigation.homeScreen
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


private var testPage : Int = 0



@Composable
fun SettingUpBottomNavigationBarAndCollapsing() {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()


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
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = NavigationItem.Companion.bottomNavigationItems.get(testPage).icon
                    ), contentDescription = "Home", modifier = Modifier
                        .clickable {

                        }
                        .size(80.dp)
                        .background(Color.White)
                )
            }
        },
        isFloatingActionButtonDocked = true,

        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.fillMaxHeight(0.1f), navController
            )
        }) {
        it
        MainScreenNavigationConfigurations(navController)


    }
}


@Composable
fun MainScreenNavigationConfigurations(
    navController: NavHostController,
) {
    NavHost(navController,
        startDestination = NavigationItem.mainHome.route) {
        homeScreen(navController)
    }
}


@Composable
fun BottomNavigationBar(modifier: Modifier, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomAppBar(
        backgroundColor = colorResource(id = R.color.white), modifier = modifier
        , cutoutShape = CircleShape
    ) {

        Row(modifier = Modifier
            .fillMaxHeight()

        ) {
            BottomNavigation(modifier = Modifier
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
                                text = item.title, fontFamily = maruBuri_Light,

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

                    if( index == 3 ){
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

