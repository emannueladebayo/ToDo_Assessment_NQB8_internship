package com.example.todoassessment

import android.app.Application
import com.example.todoassessment.di.AppContainer
import com.example.todoassessment.di.AppContainerImpl

class TodoApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}