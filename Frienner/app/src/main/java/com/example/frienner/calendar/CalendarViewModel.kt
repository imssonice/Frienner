package com.example.frienner.calendar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {

    // 출발 날짜와 끝나는 날짜를 저장하는 변수
    var startDate: LocalDate? = null
        private set
    var endDate: LocalDate? = null
        private set

    // 출발 날짜를 설정하는 함수
    fun setStartDate(date: LocalDate) {
        startDate = date
    }

    // 끝나는 날짜를 설정하는 함수
    fun setEndDate(date: LocalDate) {
        endDate = date
    }

    // 출발 날짜와 끝나는 날짜를 모두 초기화하는 함수
    fun clearDates() {
        startDate = null
        endDate = null
    }
}
