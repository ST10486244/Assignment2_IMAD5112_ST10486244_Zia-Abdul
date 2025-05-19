package za.co.varsitycollege.assignment2_imad5112_st10486244_zia_abdul

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class Questions : AppCompatActivity() {

        // Define the Quiz data class
        data class Quiz(val question: String, val isTrue: Boolean)

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

        // Sample quiz data
        private val quizData = arrayOf(
            Quiz("Nelson Mandela was South Africa’s first democratically elected president.", true),
            Quiz("The name “South Africa” has never changed since its formation.", true),
            Quiz("The national motto on the South African coat of arms is written in Afrikaans.", false),
            Quiz("The Proteas is the nickname for the South African rugby team.", false),
            Quiz("The first known use of fire by early humans was discovered in South Africa.", true)
        )

        // Track the current question and score
        private var currentIndex = 0
        private var score = 0
    private val userAnswers = mutableListOf<Boolean?>()  // Store user answers (True/False)

    private fun disableAnswerButtons() {
      trueButton.isEnabled = false
        falseButton.isEnabled = false
    }
    private fun enableAnswerButtons() {
        trueButton.isEnabled = true
         falseButton.isEnabled = true
    }

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_questions)

            // Get references to UI elements
            questionTextView = findViewById<TextView>(R.id.questionTextView)
            trueButton = findViewById<Button>(R.id.trueButton)
            falseButton = findViewById<Button>(R.id.falseButton)
            nextButton = findViewById<Button>(R.id.nextButton)


            // Display the initial question
            questionTextView.text = quizData[currentIndex].question

            // Handle the True button click
            trueButton.setOnClickListener {
                checkAnswer(true)
                disableAnswerButtons()


            }

            // Handle the False button click
            falseButton.setOnClickListener {
                checkAnswer(false)
                disableAnswerButtons()
            }

            // Handle the Next button click (to go to the next question)
            nextButton.setOnClickListener {
                if (currentIndex < quizData.size - 1) {
                    currentIndex++
                    questionTextView.text = quizData[currentIndex].question
                    enableAnswerButtons()

                } else {
                    // End of quiz, navigate to the score screen
                    navigateToScoreScreen()
                }
            }
        }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizData[currentIndex].isTrue

        // Record the user's answer
        if (userAnswers.size > currentIndex) {
            userAnswers[currentIndex] = userAnswer
        } else {
            userAnswers.add(userAnswer)
        }

        if (userAnswer == correctAnswer) {
            score++

        }
    }

        // Function to navigate to the score screen after all questions are answered
        private fun navigateToScoreScreen() {
            val intent = Intent(this, Scoresheet::class.java)
            intent.putExtra("SCORE", score)  // Passing score to the next screen
            intent.putExtra("TOTAL", quizData.size) // Passing total number of questions
            intent.putStringArrayListExtra("RESULTS", ArrayList(
                quizData.mapIndexed { index, quiz ->
                    val correct = quiz.isTrue
                    val user = userAnswers.getOrNull(index)
                    "${quiz.question}\nYour answer: $user — ${if (user == correct) "Correct" else "Incorrect"}"
                }
            ))
            startActivity(intent)
        }
}


