package com.assign3.room.db

import android.app.Application
import android.content.Context

class MyApplicationContext : Application() {
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}