package com.example.demoapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    private var email: String? = null
    private var password: String? = null
    private var rememberMe: Boolean = false

    private lateinit var loginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

//        loginBtn = findViewById(R.id.loginWig) // Initialize loginBtn
        mainBinding.loginWig.setOnClickListener{
            var intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onPause() {
        super.onPause()
        sharedPreferences = getSharedPreferences("userValues", MODE_PRIVATE)
        email = mainBinding.emailWig.text.toString()
        password = mainBinding.passwordWig.text.toString()
        rememberMe = mainBinding.checkBoxWig.isChecked

        if (rememberMe) {
            val editor = sharedPreferences.edit()
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("rememberMe", rememberMe)
            editor.apply()

            Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()
        } else {
            val editor = sharedPreferences.edit()
            editor.clear().apply() // Clear all data if "Remember Me" is not checked
        }
    }


    override fun onResume() {
        super.onResume()

        sharedPreferences = getSharedPreferences("userValues", MODE_PRIVATE)
        email = sharedPreferences.getString("email", "")
        password = sharedPreferences.getString("password", "")
        rememberMe = sharedPreferences.getBoolean("rememberMe", false)

        mainBinding.emailWig.setText(email)
        mainBinding.passwordWig.setText(password)
        mainBinding.checkBoxWig.isChecked = rememberMe
    }
}
