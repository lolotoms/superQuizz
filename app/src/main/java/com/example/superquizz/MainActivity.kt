package com.example.superquizz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.superquizz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.getRoot())
        Log.d("Activity Lyfecycle", "onCreate() called");
    }

}