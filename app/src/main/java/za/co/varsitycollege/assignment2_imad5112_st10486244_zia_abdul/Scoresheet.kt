package za.co.varsitycollege.assignment2_imad5112_st10486244_zia_abdul

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

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

        // Get the TextViews
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        val totalTextView = findViewById<TextView>(R.id.totalTextView)
        val reviewButton= findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        exitButton.setOnClickListener {
            finishAffinity() // Closes all activities in the task
            exitProcess(0)   // Terminates the process
        }

        // Set the score
        scoreTextView.text = "You scored $score out of $total"

        reviewButton.setOnClickListener {
            val reviewContent = results?.joinToString("\n\n") ?: "No review available."
            totalTextView.text = reviewContent
        }
    }
}