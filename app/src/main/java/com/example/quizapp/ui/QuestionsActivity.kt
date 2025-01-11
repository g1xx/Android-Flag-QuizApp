package com.example.quizapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var btnCheck: Button
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView
    private lateinit var tvProgress: TextView
    private lateinit var ivFlag: ImageView
    private lateinit var tvQuestion: TextView

    private var currentPosition = 0
    private lateinit var questionsList: MutableList<Question>
    private var selectedOptionPosition = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private var correctAnswered = 0

    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)

        progressBar = findViewById(R.id.pb)
        btnCheck = findViewById(R.id.btnCheck)
        tvProgress = findViewById(R.id.tvProgress)
        ivFlag = findViewById(R.id.ivFlag)
        tvQuestion = findViewById(R.id.tvQuestion)

        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)
        tvOptionFour = findViewById(R.id.tvOptionFour)

        questionsList = Constants.getQuestions()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnCheck.setOnClickListener(this)

        val questionList = Constants.getQuestions()
        Log.i("Question size", "${questionList.size}")

        showNextQuestion()

        if (intent.hasExtra(Constants.USER_NAME)) {
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }

    }
    @SuppressLint("SetTextI18n")
    private fun showNextQuestion() {


        if(currentPosition < questionsList.size) {
            btnCheck.text = "CHECK"
            currentQuestion = questionsList[currentPosition]

            resetOptions()
            val question = questionsList[currentPosition]
            ivFlag.setImageResource(question.image)
            progressBar.progress = currentPosition + 1
            tvProgress.text = "${currentPosition + 1}/${progressBar.max}"
            tvQuestion.text = question.question
            tvOptionOne.text = question.optionOne
            tvOptionTwo.text = question.optionTwo
            tvOptionThree.text = question.optionThree
            tvOptionFour.text = question.optionFour
        } else {
            btnCheck.text = "FINISH"

            val intent = Intent(this@QuestionsActivity, ResultActivity::class.java)
            intent.putExtra(Constants.USER_NAME, name)
            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswered)
            startActivity(intent)
            finish()
        }

        currentPosition++
        answered = false

    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(tvOptionOne)
        options.add(tvOptionTwo)
        options.add(tvOptionThree)
        options.add(tvOptionFour)

        for(option in options) {
            option.setTextColor(Color.parseColor("#d0d0d0"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }

    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()

        selectedOptionPosition = selectedOptionNumber

        textView.setTextColor(Color.parseColor("#444444"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.tvOptionOne ->
                selectedOption(tvOptionOne, 1)
            R.id.tvOptionTwo ->
                selectedOption(tvOptionTwo, 2)
            R.id.tvOptionThree ->
                selectedOption(tvOptionThree, 3)
            R.id.tvOptionFour ->
                selectedOption(tvOptionFour, 4)
            R.id.btnCheck -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    resetOptions()
                    showNextQuestion()
                }
                selectedOptionPosition = 0
            }

        }
    }

    private fun checkAnswer() {
        answered = true

        if (selectedOptionPosition == currentQuestion.correctAnswer) {
            highlightAnswer(selectedOptionPosition)
            correctAnswered++
        } else {
            when (selectedOptionPosition) {
                1 -> tvOptionOne.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.wrong_option_border_bg
                )
                2 -> tvOptionTwo.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.wrong_option_border_bg
                )
                3 -> tvOptionThree.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.wrong_option_border_bg
                )
                4 -> tvOptionFour.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.wrong_option_border_bg
                )
            }
        }
        btnCheck.text = getString(R.string.next)
        showSolution()
    }

    private fun showSolution() {
        selectedOptionPosition = currentQuestion.correctAnswer

        highlightAnswer(selectedOptionPosition)
    }

    private fun highlightAnswer(answer: Int) {
        when (answer) {
            1 -> tvOptionOne.background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_option_border_bg
            )
            2 -> tvOptionTwo.background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_option_border_bg
            )
            3 -> tvOptionThree.background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_option_border_bg
            )
            4 -> tvOptionFour.background = ContextCompat.getDrawable(
                this,
                R.drawable.correct_option_border_bg
            )
        }
    }


}