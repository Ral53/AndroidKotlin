package com.example.demoapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailTxt = findViewById<TextView>(R.id.emailTxt)
        val passwordTxt = findViewById<TextView>(R.id.passwordTxt)

        val sharedPreferences = getSharedPreferences("userValues", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            emailTxt.text = "No data saved"
            passwordTxt.text = ""
        } else {
            emailTxt.text = "Email: $email"
            passwordTxt.text = "Password: $password"
        }
    }
}
