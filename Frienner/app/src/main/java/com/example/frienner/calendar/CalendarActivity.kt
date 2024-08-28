package com.example.frienner.calendar

import DatePickerFragment
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.frienner.R
import java.util.Calendar

class CalendarActivity : AppCompatActivity() {

    private var departureDate: Calendar? = null
    private var arrivalDate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addschedule)

        val departureButton = findViewById<Button>(R.id.departure_button)
        val arrivalButton = findViewById<Button>(R.id.arrival_button)

        // 출발 날짜 선택 기능
        departureButton.setOnClickListener {
            val datePickerFragment = DatePickerFragment(
                onDateSelected = { selectedDate ->
                    departureButton.text = selectedDate
                    // 출발 날짜 설정
                    departureDate = Calendar.getInstance().apply {
                        val parts = selectedDate.split("-")
                        set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
                    }

                    // 출발 날짜가 재설정된 경우 오는 날짜를 초기화
                    arrivalDate = null
                    arrivalButton.text = getString(R.string.select_arrival_date) // 초기 상태로 텍스트 변경
                },
                minDate = Calendar.getInstance().timeInMillis // 오늘 날짜 이전은 선택 불가
            )
            datePickerFragment.show(supportFragmentManager, "datePickerDeparture")
        }

        // 오는 날짜 선택 기능
        arrivalButton.setOnClickListener {
            // 가는 날짜가 선택되지 않았다면, 오는 날짜는 선택할 수 없음
            if (departureDate != null) {
                val datePickerFragment = DatePickerFragment(
                    onDateSelected = { selectedDate ->
                        arrivalButton.text = selectedDate
                        // 오는 날짜 설정
                        arrivalDate = Calendar.getInstance().apply {
                            val parts = selectedDate.split("-")
                            set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
                        }
                    },
                    minDate = departureDate!!.timeInMillis // 가는 날짜 이후만 선택 가능
                )
                datePickerFragment.show(supportFragmentManager, "datePickerArrival")
            } else {
                // 가는 날짜가 선택되지 않은 경우, 안내 메시지를 표시할 수 있습니다.
                Toast.makeText(this, "먼저 출발 날짜를 선택하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
