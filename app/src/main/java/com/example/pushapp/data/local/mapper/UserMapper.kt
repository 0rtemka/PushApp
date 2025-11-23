package com.example.pushapp.data.local.mapper

import com.example.pushapp.data.local.model.entitiy.UserEntity
import com.example.pushapp.domain.model.User

fun UserEntity.toDomain(): User =
    User(id, name, experience)