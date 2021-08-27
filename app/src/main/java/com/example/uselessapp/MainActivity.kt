package com.example.uselessapp

import android.graphics.Color
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.RatingBar
import android.widget.Switch
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    lateinit var switch : Switch
    lateinit var buttonSelfDestruct : Button
    lateinit var buttonLookBusy : Button
    lateinit var rating : RatingBar
    lateinit var background : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                background.setBackgroundColor(Color.argb(100, 0, 255, 0))
                startSwitchTimer()
            }
            if(!isChecked) {
                Toast.makeText(MainActivity@this, "Switch goes off", Toast.LENGTH_SHORT).show()
                background.setBackgroundColor(Color.argb(100, 255, 255, 255))
            }

        }

    }

    private fun startSwitchTimer() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }

        }
    }

    private fun wireWidgets() {
        switch = findViewById(R.id.switchUseless)
        buttonSelfDestruct = findViewById(R.id.buttonSelf)
        buttonLookBusy = findViewById(R.id.buttonBusy)
        rating = findViewById(R.id.ratingBar)
        background = findViewById(R.id.constraintLayout)
    }
}