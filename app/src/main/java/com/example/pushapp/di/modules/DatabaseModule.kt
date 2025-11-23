package com.example.pushapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.pushapp.data.local.AppDatabase
import com.example.pushapp.data.local.callback.PrepopulateDataCallback
import com.example.pushapp.data.local.model.dao.UserDao
import com.example.pushapp.di.annotations.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        databaseProvider: Provider<AppDatabase>,
        @ApplicationScope scope: CoroutineScope,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app.db"
        )
            .addCallback(PrepopulateDataCallback(databaseProvider, scope))
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}