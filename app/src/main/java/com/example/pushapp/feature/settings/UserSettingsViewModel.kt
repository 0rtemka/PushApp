package com.example.pushapp.feature.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserSettingsViewModel : ViewModel() {

    var name by mutableStateOf("")
        private set

    var gender by mutableStateOf<Gender?>(null)
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onGenderChange(newGender: Gender) {
        gender = newGender
    }

    fun saveUserData() {
        // Здесь ты позже добавишь сохранение в БД / DataStore
        println("Saving user data: $name, $gender")
    }
}

enum class Gender(val label: String) {
    Male("Мужской"),
    Female("Женский"),
    Other("Другое")
}
