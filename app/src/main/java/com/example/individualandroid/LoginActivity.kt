package com.example.individualandroid

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.individualandroid.databinding.ActivityLoginScreenBinding

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    private var email: String? = null
    private var password: String? = null
    private var rememberMe: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.loginButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
        loginBinding.loginSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences = getSharedPreferences("userValues", MODE_PRIVATE)
        email = loginBinding.loginEmail.text.toString()
        password = loginBinding.loginPassword.text.toString()
        rememberMe = loginBinding.loginCeckBox.isChecked

        if (rememberMe) {
            val editor = sharedPreferences.edit()
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("rememberMe", rememberMe)
            editor.apply()

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

        loginBinding.loginEmail.setText(email)
        loginBinding.loginPassword.setText(password)
        loginBinding.loginCeckBox.isChecked = rememberMe
    }
}