package com.example.todoassessment.data.repository

import com.example.todoassessment.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoEntity>>
    suspend fun getTodoById(id: Int): TodoEntity?
    suspend fun upsertTodo(todo: TodoEntity)
    suspend fun deleteTodo(todo: TodoEntity)
}