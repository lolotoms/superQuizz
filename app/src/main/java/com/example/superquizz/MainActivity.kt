package com.example.superquizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Activity Lyfecycle", "onCreate() called");
    }

    override fun onStart() {
        super.onStart()
        Log.d("Activity Lyfecycle", "onStart() called");
    }

    override fun onResume() {
        super.onResume()
        Log.d("Activity Lyfecycle", "onResume() called");
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity Lyfecycle", "onPause() called");
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity Lyfecycle", "onStop() called");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Activity Lyfecycle", "onRestart() called");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity Lyfecycle", "onDestroy() called");
    }
}