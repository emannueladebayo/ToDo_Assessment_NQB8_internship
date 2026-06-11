package com.example.todoassessment.ui.todo_edit

sealed interface TodoEditEvent {
    data class OnTitleChange(val title: String) : TodoEditEvent
    data class OnDescriptionChange(val description: String) : TodoEditEvent
    data object OnSaveTodo : TodoEditEvent
}