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

        //data class
        data class Quiz(val question: String, val isTrue: Boolean)

    //declaration of UI elements
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var feedbackTextView: TextView

        //list of questions
        private val quizData = arrayOf(
            Quiz("Nelson Mandela was South Africa’s first democratically elected president.", true),
            Quiz("The name “South Africa” has never changed since its formation.", true),
            Quiz("The national motto on the South African coat of arms is written in Afrikaans.", false),
            Quiz("The Proteas is the nickname for the South African rugby team.", false),
            Quiz("The first known use of fire by early humans was discovered in South Africa.", true)
        )

    // track score and index
        private var currentIndex = 0
        private var score = 0
    // stores users answers (true, false or null)
    private val userAnswers = mutableListOf<Boolean?>()
        // disables buttons after true/ false is clicked
    private fun disableAnswerButtons() {
      trueButton.isEnabled = false
        falseButton.isEnabled = false
    }
    // enables buttons for the next question
    private fun enableAnswerButtons() {
        trueButton.isEnabled = true
         falseButton.isEnabled = true
    }
        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_questions)
            // link UI elements to variables
            questionTextView = findViewById<TextView>(R.id.questionTextView)
            trueButton = findViewById<Button>(R.id.trueButton)
            falseButton = findViewById<Button>(R.id.falseButton)
            nextButton = findViewById<Button>(R.id.nextButton)
            feedbackTextView = findViewById(R.id.feedbackTextView)
            // displays question
            questionTextView.text = quizData[currentIndex].question
            // handles true button
            trueButton.setOnClickListener {
                checkAnswer(true)
                disableAnswerButtons()


            }
            // handles false button
            falseButton.setOnClickListener {
                checkAnswer(false)
                disableAnswerButtons()
            }
            // click button to move onto next question or score screen
            nextButton.setOnClickListener {
                if (currentIndex < quizData.size - 1) {
                    currentIndex++
                    questionTextView.text = quizData[currentIndex].question
                    enableAnswerButtons()
                    feedbackTextView.text = ""

                } else {

                    navigateToScoreScreen()
                }
            }
        }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizData[currentIndex].isTrue

        //saves the users answers
        if (userAnswers.size > currentIndex) {
            userAnswers[currentIndex] = userAnswer
        } else {
            userAnswers.add(userAnswer)
        }
        //provides feedback and updates the score by incrementing
        if (userAnswer == correctAnswer) {
            score++
            feedbackTextView.text = "Correct!"
        } else {
            feedbackTextView.text = "Incorrect!"

        }
    }

        // sends quiz results to scoresheet screen
        private fun navigateToScoreScreen() {
            val intent = Intent(this, Scoresheet::class.java)
            intent.putExtra("SCORE", score)  // Passing score to the next screen
            intent.putExtra("TOTAL", quizData.size) // Passing total number of questions
           // create a list of result strings e.g "Question\nYour answer: true- Correct"
            intent.putStringArrayListExtra("RESULTS", ArrayList(
                quizData.mapIndexed { index, quiz ->
                    val correct = quiz.isTrue
                    val user = userAnswers.getOrNull(index)
                    "${quiz.question}\nYour answer: $user — ${if (user == correct) "Correct" else "Incorrect"}"
                }
            ))
            // go to scoresheet screen
             startActivity(intent)
        }
}


