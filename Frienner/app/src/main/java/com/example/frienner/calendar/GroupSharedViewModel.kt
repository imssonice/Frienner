package com.example.frienner.calendar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import java.time.LocalDate

@HiltViewModel
class GroupSharedViewModel @Inject constructor() : ViewModel() {

    // 여행 날짜를 저장하는 StateFlow
    private val _travelDates = MutableStateFlow<Pair<LocalDate?, LocalDate?>>(null to null)
    val travelDates: StateFlow<Pair<LocalDate?, LocalDate?>> get() = _travelDates

    // 여행 날짜를 설정하는 메서드
    fun setTravelDates(startDate: LocalDate, endDate: LocalDate) {
        _travelDates.value = startDate to endDate
    }
}
