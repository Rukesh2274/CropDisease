package com.example.cropdisease.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.example.cropdisease.R
import com.example.cropdisease.components.PlantDiseaseDetector
import com.example.cropdisease.data.SessionViewModel
import com.example.cropdisease.pages.fruitpage.*
import com.example.cropdisease.pages.login.Loginpage
import com.example.cropdisease.pages.signup.Signinpage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage() {

    val tabItems = listOf("Home", "Community", "Profile")
    val context = LocalContext.current
    val sessionViewModel = SessionViewModel(context)
    val viewModel = FirestoreImageTextViewModel()
    val selectedItem = remember { mutableStateOf(0) }
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val parentRouteName = navBackStackEntry.value?.destination?.parent?.route
    val routeName = navBackStackEntry.value?.destination?.route


    Scaffold(
        topBar = {
            if (routeName !in listOf("login", "signup","CommunityPage")) {
                TopAppBar(
                    title = { Text(text = "Crop Disease",
                        style = MaterialTheme.typography.titleLarge,
                    )},
                    navigationIcon = {
                        if (routeName != "HomePage" && routeName != "ProfilePage" && routeName != "CommunityPage" && routeName != "LoginPage" && routeName != "signup") {
                            OutlinedIconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        },
        bottomBar = {
            if (routeName !in listOf("login", "signup")) {
                BottomAppBar(
                    containerColor =  Color(0xFFDFE6E9),
                ) {
                    NavigationBar(containerColor = Color(0xFFDFE6E9)) {
                        tabItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = parentRouteName == item,
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = MaterialTheme.colorScheme.primary
                                ),
                                alwaysShowLabel = false,
                                onClick = {
                                    selectedItem.value = index
                                    navController.navigate(item,navOptions{
                                        popUpTo(navController.graph.findStartDestination().id){
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    })
                                },
                                icon = {
                                    when (item) {
                                        "Home" -> Icon(
                                            painter = painterResource(id = R.drawable.home),
                                            contentDescription = null
                                        )

                                        "Community" -> Icon(
                                            painter = painterResource(id = R.drawable.leaf),
                                            contentDescription = null
                                        )
                                        "Profile" -> Icon(
                                            painter = painterResource(id = R.drawable.profile),
                                            contentDescription = null) }
                                },
                                label = { Text(text = item) })
                        }
                    }
                }
            }

    }) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = if (sessionViewModel.isLoggedIn()) "Home" else "login") {
                navigation(startDestination = "HomePage", route = "Home" ) {
                    composable("HomePage" , deepLinks = listOf(NavDeepLink("deeplink://Home"))) {
                        HomePage(navController = navController)
                    }
                    composable(route = "allplants") {
                        Allplants(navController = navController)
                    }
                    composable(route = "apple") {
                        Apple(navController = navController)
                    }
                    composable(route = "bananna") {
                        Bananna(navController = navController)
                    }
                    composable(route = "mango"){
                        Mango(navController)
                    }
                    composable(route = "orange") {
                        Orange(navController = navController)
                    }
                    composable(route = "lemon") {
                        Lemon(navController = navController)
                    }
                    composable(route = "wheat") {
                        Wheat(navController = navController)
                    }
                    composable(route = "tomato") {
                        Tomato(navController = navController)
                    }
                    composable(route = "corn") {
                        Corn(navController = navController)
                    }
                    composable(route = "detectdisease") {
                        PlantDiseaseDetector(
                            context = LocalContext.current,
                            assets = LocalContext.current.assets,
                            navController = navController
                        )
                    }
                }

                navigation(startDestination = "CommunityPage", route = "Community") {

                    composable(
                        "CommunityPage",
                        deepLinks = listOf(NavDeepLink("deeplink://Community"))) {
                        ImageTextList(
                            navController = navController,
                            viewModel = FirestoreImageTextViewModel())
                    }
                    composable("{documentId}",
                        arguments = listOf(navArgument("documentId") { type = NavType.StringType })
                    ) { navBackStackEntry ->
                        val documentId = navBackStackEntry.arguments?.getString("documentId")
                        val imageText = documentId?.let { viewModel.images.find { image -> image.documentId == it } }

                        imageText?.let { DetailScreen(it) } ?: run {
                            // Handle error case
                            Text(text = "Page not found")
                        }
                    }

                }
                navigation(startDestination = "ProfilePage", route = "Profile") {
                    composable(
                        "ProfilePage", deepLinks = listOf(NavDeepLink("deeplink://Profile"))
                    ) {
                        Profile(
                            navController = navController,
                            sessionViewModel = sessionViewModel
                        )
                    }
                }
                composable(route = "edit") {
                    FirestoreImageTextInput(
                        viewModel = FirestoreImageTextViewModel(),
                        navController
                    )
                }
                composable(route = "login",deepLinks = listOf(NavDeepLink("deeplink://login"))) {
                    Loginpage(navController = navController, sessionViewModel = sessionViewModel)
                }
                composable(route = "signup") {
                    Signinpage(navController = navController ,sessionViewModel = sessionViewModel)
                }
            }
        }
    }
}