package com.example.pushapp.data.local.model.dao

import androidx.room.Query
import com.example.pushapp.data.local.model.entitiy.UserSettingsEntity
import kotlinx.coroutines.flow.Flow

interface UserSettingsDao {
    @Query("SELECT * FROM user_settings LIMIT(1)")
    fun getByCurrentUser(): Flow<UserSettingsEntity>
}