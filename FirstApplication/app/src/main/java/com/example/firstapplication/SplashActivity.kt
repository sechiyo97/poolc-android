package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({ showMain() }, 2000)
    }
    fun showMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun showToast(){
        Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show()
    }
}