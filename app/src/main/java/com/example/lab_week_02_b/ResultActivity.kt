package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val root: View = findViewById<ConstraintLayout?>(R.id.background_screen) ?: findViewById(android.R.id.content)

        val colorCode = intent?.getStringExtra(COLOR_KEY)?.trim()
        if (colorCode.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.color_code_input_empty), Toast.LENGTH_LONG).show()
        } else if (!colorCode.matches(Regex("(?i)^[0-9A-F]{6}\$"))) {
            Toast.makeText(this, getString(R.string.color_code_input_invalid), Toast.LENGTH_LONG).show()
        } else {
            try {
                root.setBackgroundColor(Color.parseColor("#$colorCode"))

                val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
                resultMessage.text = getString(R.string.color_code_result_message, colorCode.uppercase())
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, getString(R.string.color_code_input_invalid), Toast.LENGTH_LONG).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
