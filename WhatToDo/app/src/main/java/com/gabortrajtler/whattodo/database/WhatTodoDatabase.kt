package com.gabortrajtler.whattodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A database that stores -- WhatTodo -- information.
 * And a global method to get access to the database.
 *
 * This pattern is pretty much the same for any database,
 * so you can reuse it.
 */
@Database(entities = [WhatTodo::class], version = 1, exportSchema = false)
abstract class WhatTodoDatabase : RoomDatabase() {

    abstract fun whatTodoDatabaseDao(): WhatTodoDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: WhatTodoDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WhatTodoDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WhatTodoDatabase::class.java,
                    "whattodo_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .addCallback(WhatTodoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class WhatTodoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.whatTodoDatabaseDao())
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(whatTodoDatabaseDao: WhatTodoDatabaseDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            whatTodoDatabaseDao.deleteAll()

            // Add sample todos...
            var whatTodo = WhatTodo(todoText = "Hello")
            whatTodoDatabaseDao.insert(whatTodo)
            whatTodo = WhatTodo(todoText = "World!")
            whatTodoDatabaseDao.insert(whatTodo)
            whatTodo = WhatTodo(todoText = "Hard... Really...")
            whatTodoDatabaseDao.insert(whatTodo)
        }
    }
}