package com.example.individualandroid

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.individualandroid.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var registerBinding: ActivityRegisterBinding
    var data = arrayOf("Male", "Female", "Other")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.spinnerGender.onItemSelectedListener  =this
        var arrayAdapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_item, // templete
            data
        ) // dataset
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        registerBinding.spinnerGender.adapter = arrayAdapter

        registerBinding.registerButton.setOnClickListener {
            val intent = Intent(this@RegisterActivity, DashboardActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, index: Int, id: Long) {
        // or you can just parent?. or parent !- null so as not to check null
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}