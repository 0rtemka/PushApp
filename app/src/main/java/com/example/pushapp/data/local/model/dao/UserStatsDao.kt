package com.example.pushapp.data.local.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.pushapp.data.local.model.entitiy.UserStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserStatsDao {
    @Query("SELECT * FROM user_stats LIMIT(1)")
    fun getByCurrentUser(): Flow<UserStatsEntity?>
}