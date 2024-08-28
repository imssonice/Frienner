import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment(
    private val onDateSelected: (String) -> Unit,
    private val minDate: Long? = null // 최소 선택 가능 날짜
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // DatePickerDialog를 생성하고 최소 날짜 설정
        val datePickerDialog = DatePickerDialog(requireActivity(), this, year, month, day)

        // 만약 최소 날짜가 설정되어 있다면, 그것을 DatePickerDialog에 적용
        minDate?.let {
            datePickerDialog.datePicker.minDate = it
        }

        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // 날짜 선택 후 콜백을 통해 선택된 날짜를 전달
        val selectedDate = "$year-${month + 1}-$day"
        onDateSelected(selectedDate)
    }
}
