package com.mobileinsider.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.mobileinsider.app.ui.screens.*

data class TabItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun AppNavigation(
    navController: NavHostController
) {

    val tabs = listOf(

        TabItem(
            "home",
            "Home",
            Icons.Default.Home
        ),

        TabItem(
            "discover",
            "Discover",
            Icons.Default.Explore
        ),

        TabItem(
            "tools",
            "Tools",
            Icons.Default.Build
        ),

        TabItem(
            "ai",
            "AI",
            Icons.Default.AutoAwesome
        ),

        TabItem(
            "profile",
            "Profile",
            Icons.Default.Person
        )
    )

    val backStackEntry by
        navController.currentBackStackEntryAsState()

    val currentRoute =
        backStackEntry?.destination?.route

    Scaffold(

        bottomBar = {

            if (
                tabs.any {
                    it.route == currentRoute
                }
            ) {

                NavigationBar {

                    tabs.forEach { tab ->

                        NavigationBarItem(

                            selected =
                                currentRoute ==
                                    tab.route,

                            onClick = {

                                navController.navigate(
                                    tab.route
                                ) {

                                    popUpTo("home") {
                                        saveState = true
                                    }

                                    launchSingleTop = true

                                    restoreState = true
                                }
                            },

                            icon = {

                                Icon(
                                    tab.icon,
                                    contentDescription =
                                        tab.title
                                )
                            },

                            label = {

                                Text(tab.title)
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->

        NavHost(

            navController =
                navController,

            startDestination =
                "splash",

            modifier =
                Modifier
                    .then(
                        Modifier
                    )
        ) {

            composable("splash") {

                SplashScreen {

                    navController.navigate(
                        "home"
                    ) {

                        popUpTo("splash") {
                            inclusive = true
                        }
                    }
                }
            }

            composable("home") {

                HomeScreen { route ->

                    navController.navigate(route)
                }
            }

            composable("discover") {

                DiscoverScreen { route ->

                    navController.navigate(route)
                }
            }

            composable("tools") {

                ToolsScreen { route ->

                    navController.navigate(route)
                }
            }

            composable("ai") {

                AiScreen()
            }

            composable("profile") {

                ProfileScreen()
            }

            composable("news") {

                NewsScreen()
            }

            composable("videos") {

                VideosScreen()
            }

            composable("phones") {

                CategoryScreen(
                    "Phones",
                    "📱",
                    "Smartphones, specifications and mobile technology."
                )
            }

            composable("laptops") {

                CategoryScreen(
                    "Laptops",
                    "💻",
                    "Laptops, notebooks and PC technology."
                )
            }

            composable("cameras") {

                CategoryScreen(
                    "Cameras",
                    "📷",
                    "Cameras and creator equipment."
                )
            }

            composable("compare") {

                CompareScreen()
            }

            composable("unlock") {

                UnlockScreen()
            }

            composable("resources") {

                ResourcesScreen()
            }

            composable("team") {

                TeamScreen()
            }

            composable("contact") {

                ContactScreen()
            }
        }
    }
}
