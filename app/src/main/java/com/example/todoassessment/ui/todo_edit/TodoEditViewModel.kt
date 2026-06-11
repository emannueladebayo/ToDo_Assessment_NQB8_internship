package com.example.todoassessment.ui.todo_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoassessment.TodoApplication
import com.example.todoassessment.data.local.TodoEntity
import com.example.todoassessment.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TodoEditViewModel(private val repository: TodoRepository) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private var currentTodoId: Int = 0

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    sealed interface UiEvent {
        data object SaveSuccess : UiEvent
    }

    fun loadTodo(id: Int) {
        if (id == -1 || id == currentTodoId) return
        viewModelScope.launch {
            repository.getTodoById(id)?.let { todo ->
                currentTodoId = todo.id
                title = todo.title
                description = todo.description
            }
        }
    }

    fun onEvent(event: TodoEditEvent) {
        when (event) {
            is TodoEditEvent.OnTitleChange -> title = event.title
            is TodoEditEvent.OnDescriptionChange -> description = event.description
            is TodoEditEvent.OnSaveTodo -> {
                if (title.isBlank()) return
                viewModelScope.launch {
                    repository.upsertTodo(
                        TodoEntity(
                            id = if (currentTodoId != 0) currentTodoId else 0,
                            title = title,
                            description = description
                        )
                    )
                    _uiEvent.emit(UiEvent.SaveSuccess)
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)
                TodoEditViewModel(application.container.todoRepository)
            }
        }
    }
}