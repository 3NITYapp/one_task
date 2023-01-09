package com.task.onetaskapp.di

import android.content.Context
import androidx.room.Room
import com.task.onetaskapp.data.database.OneTaskDatabase
import com.task.onetaskapp.data.database.TaskDao
import com.task.onetaskapp.data.datastore.ThemeManager
import com.task.onetaskapp.data.datastore.ThemeManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): ThemeManager {
        return ThemeManagerImpl(context = context)
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: OneTaskDatabase): TaskDao = database.getTaskDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): OneTaskDatabase =
        Room.databaseBuilder(
            context,
            OneTaskDatabase::class.java,
            "task-db"
        ).fallbackToDestructiveMigration().build()
}