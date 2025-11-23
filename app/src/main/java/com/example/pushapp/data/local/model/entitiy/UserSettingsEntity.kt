package com.example.pushapp.data.local.model.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_settings",
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
data class UserSettingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "notification_time")
    val notificationTime: String,
    @ColumnInfo(name = "rest_time_sec")
    val restTimeSec: Int,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
)