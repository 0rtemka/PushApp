package com.example.pushapp.data.local.model.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainings")
data class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "feedback_id")
    val feedbackId: Int,
    val calories: Int,
    val pushUps: Int,
    @ColumnInfo(name = "duration_sec")
    val durationSec: Int,
    val started: Long,
    val finished: Long,
)