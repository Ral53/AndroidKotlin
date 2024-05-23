package com.example.classwork

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.classwork.databinding.ActivityMain2Binding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private var score = 0
    private var lives = 3
    private var timer: CountDownTimer? = null
    private var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.againBtn.setOnClickListener {
            checkAnswer()
        }

        binding.exitBtn.setOnClickListener {
            generateQuestion()
        }

        generateQuestion()
    }

    private fun generateQuestion() {
        val operation = intent.getStringExtra("operation")
        if (operation == null) {
            finish()
            return
        }

        val num1 = Random.nextInt(1, 10)
        val num2 = Random.nextInt(1, 10)
        val question = when (operation) {
            "addition" -> {
                correctAnswer = num1 + num2
                "$num1 + $num2 = ?"
            }
            "subtraction" -> {
                correctAnswer = num1 - num2
                "$num1 - $num2 = ?"
            }
            "multiplication" -> {
                correctAnswer = num1 * num2
                "$num1 * $num2 = ?"
            }
            else -> {
                correctAnswer = 0
                "Invalid Operation"
            }
        }

        binding.questionView.text = question
        resetTimer()
    }

    private fun checkAnswer() {
        val userAnswer = binding.solutionView.text.toString().toIntOrNull()
        if (userAnswer == correctAnswer) {
            score++
            binding.scoreNumber.text = score.toString()
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            lives--
            binding.lifeNumber.text = lives.toString()
            if (lives == 0) {
                endGame()
                return
            }
            Toast.makeText(this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show()
        }
        generateQuestion()
    }

    private fun resetTimer() {
        timer?.cancel()
        binding.timeNumber.text = "30"
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timeNumber.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                lives--
                binding.lifeNumber.text = lives.toString()
                if (lives == 0) {
                    endGame()
                } else {
                    Toast.makeText(this@MainActivity2, "Time's up! Try again.", Toast.LENGTH_SHORT).show()
                    generateQuestion()
                }
            }
        }.start()
    }

    private fun endGame() {
        Toast.makeText(this, "Game Over! Your score: $score", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity3::class.java).apply {
            putExtra("score", score)
        }
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
