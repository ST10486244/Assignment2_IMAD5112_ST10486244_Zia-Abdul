package za.co.varsitycollege.assignment2_imad5112_st10486244_zia_abdul

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

// Displays the user's score and allows them to review their answers
class Scoresheet : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scoresheet)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val results = intent.getStringArrayListExtra("RESULTS")
        val correctAnswers = intent.getStringArrayListExtra("CORRECT_ANSWERS")

       //Link UI elements to variables
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        val totalTextView = findViewById<TextView>(R.id.totalTextView)
        val reviewButton= findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)
        // Handles "Exit" button click: Closes the app completely- Terminates app
        exitButton.setOnClickListener {
            finishAffinity() //
            exitProcess(0)
        }

        // Final score display
        scoreTextView.text = "You scored $score out of $total"

        // combines the question and users answer for a full review
        reviewButton.setOnClickListener {
            val reviewContent = results?.joinToString("\n\n") ?: "No review available."
            totalTextView.text = reviewContent
        }
    }
}