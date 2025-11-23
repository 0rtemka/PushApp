package com.example.pushapp.data.local.model.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "user_achievements",
    primaryKeys = ["user_id", "achievement_id"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AchievementEntity::class,
            parentColumns = ["id"],
            childColumns = ["achievement_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("user_id"), Index("achievement_id")]
)
data class UserAchievementEntity(
    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "achievement_id")
    val achievementId: Int,

    @ColumnInfo(name = "received_at")
    val receivedAt: Long
)