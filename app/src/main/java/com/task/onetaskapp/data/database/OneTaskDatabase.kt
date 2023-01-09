package com.task.onetaskapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.onetaskapp.model.task.Task

@Database(entities = [Task::class], version = 3, exportSchema = false)
abstract class OneTaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}