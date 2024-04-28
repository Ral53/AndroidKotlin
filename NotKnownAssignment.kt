package com.example.demoapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class NotKnownAssignment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_not_known_assignment) // Set your XML layout here

        val dateView: TextView = findViewById(R.id.dateView)
        val timeView: TextView = findViewById(R.id.timeView)
        val addBtn: Button = findViewById(R.id.addbtn)
        val spinner: Spinner = findViewById(R.id.spinner)
        val imageView: ImageView = findViewById(R.id.imageView)
        val taskName: TextView = findViewById(R.id.taskName)
//        val checkBox: CheckBox = findViewById(R.id.checkBox)
        val line1: View = findViewById(R.id.line1)
        val line2: View = findViewById(R.id.line2)

        // Set up Date Picker
        dateView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    dateView.text = "$year/${monthOfYear + 1}/$dayOfMonth"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // Set up Time Picker
        timeView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    timeView.text = "$hourOfDay:$minute"
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }

        // Set up Spinner
        val taskTypes = arrayOf("Type A", "Type B", "Type C")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, taskTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Button click listener
        addBtn.setOnClickListener {
            val taskType = spinner.selectedItem.toString()
            val taskDescription = taskName.text.toString()
            val date = dateView.text.toString()
            val time = timeView.text.toString()
//            val isCompleted = checkBox.isChecked


            Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show()
        }

        // Enable editing the task name
        taskName.setOnClickListener {
            val editText = EditText(this)
            editText.setText(taskName.text)
            AlertDialog.Builder(this)
                .setTitle("Edit Task Name")
                .setView(editText)
                .setPositiveButton("OK") { dialog, _ ->
                    taskName.text = editText.text.toString()
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun enableEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets ->
            val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            window.decorView.setOnApplyWindowInsetsListener(null)
            insets
        }
    }
}
