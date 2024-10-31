package com.stu71954.jobroomdb_71954.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stu71954.jobroomdb_71954.decoratorscreens.DecoratorDetails
import com.stu71954.jobroomdb_71954.decoratorscreens.SearchScreen
import com.stu71954.jobroomdb_71954.data.mockDecorators

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "search") {
        composable("search") { SearchScreen(navController) }
        composable("details/{decoratorId}") { backStackEntry ->
            val decoratorId = backStackEntry.arguments?.getString("decoratorId")?.toInt()
            val decorator = mockDecorators.find { it.id == decoratorId }
            decorator?.let { DecoratorDetails(decorator = it) }
        }
    }
}