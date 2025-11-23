package com.example.pushapp.data.local.model.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_feedback")
data class TrainingFeedbackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Int,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
)