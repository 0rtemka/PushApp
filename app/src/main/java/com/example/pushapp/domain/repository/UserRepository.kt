package com.example.pushapp.domain.repository

import com.example.pushapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<User?>
}