package com.example.frienner.calendar;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.frienner.R
import com.example.frienner.databinding.FragmentMaterialCalendarBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class MaterialCalendarFragment : Fragment() {
    private var _binding: FragmentMaterialCalendarBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: GroupSharedViewModel by activityViewModels()

    // 출발 날짜와 끝나는 날짜를 선택하기 위한 변수
    private var selectedStartDate: CalendarDay? = null
    private var selectedEndDate: CalendarDay? = null

    // 데코레이터 변수를 나중에 초기화하기 위해 lateinit 키워드로 선언
    private lateinit var dayDecorator: DayViewDecorator
    private lateinit var todayDecorator: DayViewDecorator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        with(calendarView) {
            // 데코레이터 초기화
            dayDecorator = CalendarDecorators.dayDecorator(requireContext())
            todayDecorator = CalendarDecorators.todayDecorator(requireContext())

            // 캘린더뷰에 데코레이터 추가
            addDecorators(dayDecorator, todayDecorator)

            // 요일 텍스트 포맷터 설정
            setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

            // 날짜 변경 리스너 설정
            setOnDateChangedListener { widget, date, selected ->
                if (selected) {
                    if (selectedStartDate == null || (selectedStartDate != null && selectedEndDate != null)) {
                        // 첫 번째 날짜 선택 (출발 날짜)
                        selectedStartDate = date
                        selectedEndDate = null
                        widget.clearSelection()
                        widget.setDateSelected(selectedStartDate, true)
                    } else if (selectedStartDate != null && selectedEndDate == null) {
                        // 두 번째 날짜 선택 (끝나는 날짜)
                        selectedEndDate = date
                        widget.selectRange(selectedStartDate, selectedEndDate)
                        sharedViewModel.setTravelDates(
                            selectedStartDate!!.toLocalDate(),
                            selectedEndDate!!.toLocalDate()
                        )
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun CalendarDay.toLocalDate(): LocalDate {
        return LocalDate.of(year, month, day)
    }
}
