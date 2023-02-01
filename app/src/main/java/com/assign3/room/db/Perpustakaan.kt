/**
 * Created by maulana kavaldo on 18/12/2020.
 */
 
 package com.assign3.room.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = arrayOf(Buku::class))
abstract class Perpustakaan : RoomDatabase() {
    abstract fun bookDao(): BukuDao
    companion object {

        private const val NAME = "Buku"
        private var INSTANCE: Perpustakaan? = null

        fun getInstance(): Perpustakaan {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    MyApplicationContext.ApplicationContext,
                    Perpustakaan::class.java,
                    NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as Perpustakaan
        }
    }
}