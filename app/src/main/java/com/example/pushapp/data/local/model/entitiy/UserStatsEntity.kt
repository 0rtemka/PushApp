package com.example.pushapp.data.local.model.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_stats",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("user_id")]
)
data class UserStatsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val level: Int,
    val experience: Int,
    val totalPushUps: Int,
    val totalCalories: Int,
    val currentStreak: Int,
    val bestStreak: Int,
    val lastTrainingDate: Long,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
)