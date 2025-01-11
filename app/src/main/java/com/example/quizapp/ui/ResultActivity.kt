package com.example.quizapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.utils.Constants

class ResultActivity : AppCompatActivity() {
    private lateinit var name: String
    private var correctAnswered: Int = 0
    private var totalQuestions: Int = 0

    private lateinit var tvResult: TextView
    private lateinit var tvName: TextView
    private lateinit var btnFinish: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        tvResult = findViewById(R.id.text_result)
        tvName = findViewById(R.id.textview_score)
        btnFinish = findViewById(R.id.finish_button)

        if (intent.hasExtra(Constants.USER_NAME))
            name = intent.getStringExtra(Constants.USER_NAME)!!
        correctAnswered = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        tvResult.text = "${correctAnswered}/${totalQuestions}"
        tvName.text = "${name}"

        btnFinish.setOnClickListener {
            Intent(this@ResultActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}


