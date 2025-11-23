package com.example.pushapp.data.local.model.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val coefficient: Double,
    val calories: Int,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
)