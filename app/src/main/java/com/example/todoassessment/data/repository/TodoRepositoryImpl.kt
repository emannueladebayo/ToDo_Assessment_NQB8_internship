package com.example.todoassessment.data.repository

import com.example.todoassessment.data.local.TodoDao
import com.example.todoassessment.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {
    override fun getAllTodos(): Flow<List<TodoEntity>> = dao.getAllTodos()

    override suspend fun getTodoById(id: Int): TodoEntity? = dao.getTodoById(id)

    override suspend fun upsertTodo(todo: TodoEntity) = dao.upsertTodo(todo)
    override suspend fun deleteTodo(todo: TodoEntity) = dao.deleteTodo(todo)
}