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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // button that starts the quiz
        val beginButton = findViewById<Button>(R.id.beginButton)
        beginButton.setOnClickListener {

            val intent = Intent(this, Questions::class.java)
            startActivity(intent)
        }
    }
}





