package com.example.pushapp.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(

) : ViewModel() {
    private val today = LocalDate.now()

    private val _selectedDate = MutableStateFlow(today)
    val selectedDate = _selectedDate.asStateFlow()

    private val _currentMonth = MutableStateFlow(YearMonth.now())
    val currentMonth = _currentMonth.asStateFlow()

    val days: StateFlow<List<LocalDate>> =
        _currentMonth.map { ym ->
            generateDaysForMonth(ym)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, generateDaysForMonth(YearMonth.now()))

    fun nextMonth() {
        _currentMonth.value = _currentMonth.value.plusMonths(1)
    }

    fun previousMonth() {
        _currentMonth.value = _currentMonth.value.minusMonths(1)
    }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun isToday(day: LocalDate): Boolean {
        return day == today
    }

    private fun generateDaysForMonth(month: YearMonth): List<LocalDate> {
        val firstDay = month.atDay(1)
        val lastDay = month.atEndOfMonth()

        val firstWeekDay = (firstDay.dayOfWeek.value + 6) % 7
        val list = mutableListOf<LocalDate>()

        repeat(firstWeekDay) {
            list.add(firstDay.minusDays((firstWeekDay - it).toLong()))
        }

        for (i in 1..lastDay.dayOfMonth) {
            list.add(month.atDay(i))
        }

        while (list.size % 7 != 0) {
            val nextDay = list.last().plusDays(1)
            list.add(nextDay)
        }

        return list
    }
}