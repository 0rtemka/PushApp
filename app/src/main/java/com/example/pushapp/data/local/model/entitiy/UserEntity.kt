package com.example.pushapp.data.local.model.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val experience: Int,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
)