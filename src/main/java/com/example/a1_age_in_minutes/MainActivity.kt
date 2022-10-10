package com.example.a1_age_in_minutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.a1_age_in_minutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectDateBtn.setOnClickListener { onClickDatePicker() }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun onClickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year: Int = myCalendar.get(Calendar.YEAR)
        val month: Int = myCalendar.get(Calendar.MONTH)
        val day: Int = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, i, i2, i3 ->
                Toast.makeText(
                    this,
                    "Datepicker works", Toast.LENGTH_LONG
                ).show()
                val date = "$i3/${i2 + 1}/$i"
                binding.dateTextView.text = date

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val parsedDate = sdf.parse(date)

                val selectedDateInMinutes = parsedDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                binding.timeInMinutesTV.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }

}