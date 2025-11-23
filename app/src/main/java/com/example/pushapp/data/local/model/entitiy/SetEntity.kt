package com.example.pushapp.data.local.model.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sets")
data class SetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,
    @ColumnInfo(name = "training_id")
    val trainingId: Int,
    @ColumnInfo(name = "duration_sec")
    val durationSec: Int,
    val reps: Int,
    val calories: Int,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
)