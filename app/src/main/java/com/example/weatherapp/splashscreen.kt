package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
          Handler().postDelayed({
              val a=Intent(applicationContext,MainActivity::class.java)
              startActivity(a)
              finish()
          },2000)

    }
}