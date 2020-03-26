package com.gabortrajtler.whattodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
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

    private class WhatTodoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var whatTodoDatabaseDao = database.whatTodoDatabaseDao()

                    // Delete all content here.
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
    }

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
                    .addCallback(WhatTodoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}