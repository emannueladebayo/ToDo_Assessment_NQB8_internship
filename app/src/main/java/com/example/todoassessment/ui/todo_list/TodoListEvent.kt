package com.example.todoassessment.ui.todo_list

import com.example.todoassessment.data.local.TodoEntity

sealed interface TodoListEvent {
    data class OnDeleteTodo(val todo: TodoEntity) : TodoListEvent
    data class OnToggleComplete(val todo: TodoEntity, val isCompleted: Boolean) : TodoListEvent
}