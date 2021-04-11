package com.amatucci.andrea.beers.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.amatucci.andrea.beers.R
import com.amatucci.andrea.beers.databinding.DialogMonthYearPickerBinding
import java.util.*

class MonthYearPickerDialog(private val date: Date = Date()) : DialogFragment() {

    companion object {
        private const val MIN_YEAR = 1990
    }

    private lateinit var binding: DialogMonthYearPickerBinding

    private var listener: DatePickerDialog.OnDateSetListener? = null

    fun setListener(listener: DatePickerDialog.OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogMonthYearPickerBinding.inflate(requireActivity().layoutInflater)
        val cal: Calendar = Calendar.getInstance().apply { time = date }

        binding.pickerMonth.run {
            minValue = 0
            maxValue = 11
            value = cal.get(Calendar.MONTH)
            displayedValues = arrayOf(
                getString(R.string.month_Jan),
                getString(R.string.month_Feb),
                getString(R.string.month_Mar),
                getString(R.string.month_Apr),
                getString(R.string.month_May),
                getString(R.string.month_Jun),
                getString(R.string.month_Jul),
                getString(R.string.month_Aug),
                getString(R.string.month_Sep),
                getString(R.string.month_Oct),
                getString(R.string.month_Nov),
                getString(R.string.month_Dec)
            )
        }

        binding.pickerYear.run {
            val year = cal.get(Calendar.YEAR)
            minValue = MIN_YEAR
            maxValue = year
            value = year
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialogTitle))
            .setView(binding.root)
            .setPositiveButton(getString(R.string.ok)) { _, _ -> listener?.onDateSet(null, binding.pickerYear.value, binding.pickerMonth.value + 1, 1) }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> dialog?.cancel() }
            .create()
    }
}