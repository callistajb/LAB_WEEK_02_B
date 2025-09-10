package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
        val backButton = findViewById<Button>(R.id.back_button)

        val colorCode = intent.getStringExtra(COLOR_KEY)
        if (!colorCode.isNullOrEmpty()) {
            try {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
                resultMessage.text = getString(R.string.color_code_result_message, colorCode.uppercase())
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, getString(R.string.color_code_input_invalid), Toast.LENGTH_LONG).show()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
