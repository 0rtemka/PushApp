package com.example.pushapp.data.local.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pushapp.data.local.AppDatabase
import com.example.pushapp.data.local.model.entitiy.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Provider

class PrepopulateDataCallback(
    private val databaseProvider: Provider<AppDatabase>,
    private val applicationScope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        applicationScope.launch(Dispatchers.IO) {
            val database = databaseProvider.get()
            val dao = database.userDao()

            dao.insertUser(
                UserEntity(
                    name = "Test",
                    experience = 0
                )
            )
        }
    }
}