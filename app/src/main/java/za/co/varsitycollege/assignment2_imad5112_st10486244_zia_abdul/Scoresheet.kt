package za.co.varsitycollege.assignment2_imad5112_st10486244_zia_abdul

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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
        val resultTextView = findViewById<TextView>(R.id.totalTextView)
        val exitButton = findViewById<Button>(R.id.exitButton)


        // Set the score
        scoreTextView.text = "You scored $score out of $total"

        // Set the results
        resultTextView.text = results?.joinToString("\n\n") ?: "No results"
    }
}