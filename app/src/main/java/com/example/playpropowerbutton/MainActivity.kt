package com.example.playpropowerbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val powerButton = findViewById<QueuePowerButton>(R.id.power_button)
        val size = getScreenSize(this)
        powerButton.circlePadding = (size?.width?.times(0.0157))?.toFloat() ?: 17F
        powerButton.setOnClickListener {
            powerButton.startAnimation()
        }
    }
}