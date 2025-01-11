package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val CORRECT_ANSWERS = "correct_answers"

    fun getQuestions(): MutableList<Question> {
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            1, "What country does this flag belong?",
            R.drawable.italy_flag, "Italy", "India", "Iran", "Ireland", 1
        )
        questions.add(quest1)

        val quest2 = Question(
            1, "What country does this flag belong?",
            R.drawable.romania_flag, "Poland", "India", "Romania", "Ireland", 3
        )
        questions.add(quest2)

        val quest3 = Question(
            1, "What country does this flag belong?",
            R.drawable.finland_flag, "Italy", "Ukraine", "Slovakia", "Finland", 4
        )
        questions.add(quest3)

        val quest4 = Question(
            1, "What country does this flag belong?",
            R.drawable.argentina_flag, "Armenia", "Netherlands", "Argentina", "Brazil", 3
        )
        questions.add(quest4)

        val quest5 = Question(
            1, "What country does this flag belong?",
            R.drawable.haiti_flag, "Greenland", "Haiti", "Mexico", "Latvia", 2
        )
        questions.add(quest5)

        val quest6 = Question(
            1, "What country does this flag belong?",
            R.drawable.brazil_flag, "Brazil", "Barcelona", "Belgian", "Canada", 1
        )
        questions.add(quest6)

        val quest7 = Question(
            1, "What country does this flag belong?",
            R.drawable.india_flag, "Italy", "India", "France", "Lita", 2
        )
        questions.add(quest7)

        val quest8 = Question(
            1, "What country does this flag belong?",
            R.drawable.germany_flag, "Greenland", "Haiti", "German", "Czech", 3
        )
        questions.add(quest8)

        val quest9 = Question(
            1, "What country does this flag belong?",
            R.drawable.nigeria_flag, "Nigeria", "Niger", "Netherlands", "Denmark", 1
        )
        questions.add(quest9)

        val quest10 = Question(
            1, "What country does this flag belong?",
            R.drawable.spain_flag, "Portugal", "Mexico", "Moldova", "Spain", 4
        )
        questions.add(quest10)

        return questions
    }
}