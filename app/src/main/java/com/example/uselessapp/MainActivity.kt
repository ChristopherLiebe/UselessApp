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
import kotlin.Unit as Unit1

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

    private fun lookBusyScreen() {
        progressBar.progress = 0
        background.setBackgroundColor(Color.argb(100, 255, 255, 255))
        cancelButton.setOnClickListener() {
            uselessWidgets()
        }
        busyWidgets()
        startLookBusyTimer()
    }

    @SuppressLint("WrongConstant")
    private fun busyWidgets() {
        finishButton.isEnabled = false
        switch.visibility = INVISIBLE
        buttonSelfDestruct.visibility = INVISIBLE
        buttonLookBusy.visibility = INVISIBLE
        rating.visibility = INVISIBLE
        cancelButton.visibility = VISIBLE
        finishButton.visibility = VISIBLE
        progressBarCaption.visibility = VISIBLE
        progressBar.visibility = VISIBLE
    }

    @SuppressLint("WrongConstant")
    private fun uselessWidgets() {
        switch.visibility = VISIBLE
        buttonSelfDestruct.visibility = VISIBLE
        buttonLookBusy.visibility = VISIBLE
        rating.visibility = VISIBLE
        cancelButton.visibility = INVISIBLE
        finishButton.visibility = INVISIBLE
        progressBarCaption.visibility = INVISIBLE
        progressBar.visibility = INVISIBLE
    }

    private fun startLookBusyTimer() {
        val busyTimer = object : CountDownTimer(5150, 50) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress++
            }

            override fun onFinish() {
                finishButton.isEnabled = true
                finishButton.setOnClickListener() {
                    uselessWidgets()
                }

            }

        }

        busyTimer.start()
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
        var flashDistance = 50
        var flashTimeout = 0
        var flashCount = 0

        val redTimer = object : CountDownTimer(250, 1) {
            override fun onTick(millisUntilFinished: Long) {
                background.setBackgroundColor(Color.argb(100, 255, 0 ,0))
            }

            override fun onFinish() {
                background.setBackgroundColor(Color.argb(100, 255, 255 ,255))
            }
        }

        val uselessTimer = object : CountDownTimer(10000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                if (flashTimeout >= flashDistance) {
                    flashCount++
                    flashTimeout = 0
                    redTimer.start()
                }
                if (flashCount >= 5) {
                    flashDistance -= 15
                    flashCount = 0
                }
                flashTimeout++
                buttonSelfDestruct.text = "${millisUntilFinished/1000}"
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
        progressBar.max = 100

    }
}
