package com.example.todoassessment.di

import android.content.Context
import androidx.room.Room
import com.example.todoassessment.data.local.TodoDatabase
import com.example.todoassessment.data.repository.TodoRepository
import com.example.todoassessment.data.repository.TodoRepositoryImpl

interface AppContainer {
    val todoRepository: TodoRepository
}

class AppContainerImpl(private val context: Context) : AppContainer {
    private val database: TodoDatabase by lazy {
        Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo_assessment_db"
        ).build()
    }

    override val todoRepository: TodoRepository by lazy {
        TodoRepositoryImpl(database.todoDao)
    }
}