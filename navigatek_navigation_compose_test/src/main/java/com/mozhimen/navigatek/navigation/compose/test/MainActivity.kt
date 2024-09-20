package com.mozhimen.navigatek.navigation.compose.test

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

enum class ERoute {
    FIRST,
    SECOND,
    THIRD
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ERoute.FIRST.name) {
        composable(
            route = ERoute.FIRST.name
        ) {
            PageFirst(navController)
        }
        composable(
            route = ERoute.SECOND.name
        ) {
            PageSecond(navController)
        }
        composable(
            route = ERoute.THIRD.name
        ) {
            PageThird(navController)
        }
    }
}

@Composable
fun PageFirst(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = "PageFirst",
            modifier = Modifier
                .clickable {
                    navController.navigate("second")
                })
    }
}

@Composable
fun PageSecond(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = "PageSecond", modifier = Modifier
            .clickable {
                navController.navigate("third")
            })
    }
}

@Composable
fun PageThird(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = "PageThird", modifier = Modifier
            .clickable {
                navController.popBackStack()
            })
    }
}


