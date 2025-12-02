package com.example.pushapp.data.repository

import com.example.pushapp.data.local.mapper.toDomain
import com.example.pushapp.data.local.model.dao.UserDao
import com.example.pushapp.domain.model.User
import com.example.pushapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getCurrentUser(): Flow<User?> {
        return userDao.getCurrentUser().map { entity ->
            entity?.toDomain()
        };
    }
}