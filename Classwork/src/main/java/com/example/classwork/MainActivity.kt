import com.example.classwork.MainActivity2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.classwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addition.setOnClickListener {
            startQuizActivity("addition")
        }

        binding.subtrction.setOnClickListener {
            startQuizActivity("subtraction")
        }

        binding.multiplication.setOnClickListener {
            startQuizActivity("multiplication")
        }
    }

    private fun startQuizActivity(operation: String) {
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("operation", operation)
        }
        startActivity(intent)
    }
}
