package com.example.todoassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoassessment.ui.todo_edit.TodoEditScreen
import com.example.todoassessment.ui.todo_edit.TodoEditViewModel
import com.example.todoassessment.ui.todo_list.TodoListScreen
import com.example.todoassessment.ui.todo_list.TodoListViewModel
import com.example.todoassessment.ui.theme.ToDoAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAssessmentTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "todo_list"
                ) {
                    composable("todo_list") {
                        val todoListViewModel: TodoListViewModel = viewModel(factory = TodoListViewModel.Factory)
                        TodoListScreen(
                            onNavigateToEdit = { id ->
                                navController.navigate("todo_edit/$id")
                            },
                            viewModel = todoListViewModel
                        )
                    }
                    composable(
                        route = "todo_edit/{todoId}",
                        arguments = listOf(navArgument("todoId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1
                        val todoEditViewModel: TodoEditViewModel = viewModel(factory = TodoEditViewModel.Factory)
                        TodoEditScreen(
                            todoId = todoId,
                            onPopBackStack = { navController.popBackStack() },
                            viewModel = todoEditViewModel
                        )
                    }
                }
            }
        }
    }
}