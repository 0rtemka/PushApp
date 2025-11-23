package com.example.pushapp.data.local.model.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val icon: String,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
)