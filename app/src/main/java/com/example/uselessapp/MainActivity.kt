package com.example.uselessapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.INVISIBLE
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    lateinit var switch : Switch
    lateinit var buttonSelfDestruct : Button
    lateinit var buttonLookBusy : Button
    lateinit var rating : RatingBar
    lateinit var background : ConstraintLayout
    lateinit var cancelButton : Button
    lateinit var finishButton : Button
    lateinit var progressBar : ProgressBar
    lateinit var progressBarCaption : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                Toast.makeText(MainActivity@this, "Switch goes on", Toast.LENGTH_SHORT).show()
                background.setBackgroundColor(Color.argb(100, 0, 255, 0))
                startSwitchTimer()
            }
            if(!isChecked) {
                Toast.makeText(MainActivity@this, "Switch goes off", Toast.LENGTH_SHORT).show()
                background.setBackgroundColor(Color.argb(100, 255, 255, 255))
            }
        }

        buttonSelfDestruct.setOnClickListener() {
            startDestructTimer()
        }

        buttonLookBusy.setOnClickListener() {
            lookBusyScreen()
        }
    }

    @SuppressLint("WrongConstant")
    private fun lookBusyScreen() {
        switch.visibility = INVISIBLE
        buttonSelfDestruct.visibility = INVISIBLE
        buttonLookBusy.visibility = INVISIBLE
        rating.visibility = INVISIBLE
        cancelButton.visibility = VISIBLE
        finishButton.visibility = VISIBLE
        progressBar.visibility = VISIBLE
        progressBarCaption.visibility = VISIBLE

    }

    private fun startSwitchTimer() {
        // make an anonymous inner class to create a CountDownTimer object
        val uselessTimer = object : CountDownTimer(3000, 500) {
            // callbacks -- functions that will be called later, sometime in the future
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                // Display the number of seconds remaining on the button
                // to close the activity: finish()
                switch.isChecked = false
            }
        }
        uselessTimer.start()
    }

    private fun startDestructTimer() {
        var isRed = false
        var flashDuration = 500
        var flashTimeout = 0

        val uselessTimer = object : CountDownTimer(10000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                flashTimeout++
                if(flashDuration <= flashTimeout) {
                    if (!isRed) {
                        background.setBackgroundColor(Color.argb(200, (((Math.random())*155)+ 100).toInt(), 0, 0))
                    }
                    else {
                        background.setBackgroundColor(Color.argb(100, 255, 255, 255))
                    }
                    flashDuration -= 50
                    flashTimeout = 0
                }
                buttonSelfDestruct.text = "${millisUntilFinished}"
            }

            override fun onFinish() {
                finish()
            }
        }

        uselessTimer.start()
    }

    private fun wireWidgets() {
        switch = findViewById(R.id.switchUseless)
        buttonSelfDestruct = findViewById(R.id.buttonSelf)
        buttonLookBusy = findViewById(R.id.buttonBusy)
        rating = findViewById(R.id.ratingBar)
        background = findViewById(R.id.constraintLayout)
        cancelButton = findViewById(R.id.cancelButton)
        finishButton = findViewById(R.id.finishButton)
        progressBar = findViewById(R.id.progressBar)
        progressBarCaption = findViewById(R.id.progressBarCaption)

    }
}