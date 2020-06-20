package com.example.mathtutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private var buttonOpen: Button? = null
    private var buttonSimultaneous: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onClickButtonListener()
        onClickButtonSimultaneousListener()
    }

    private fun onClickButtonListener() {
        buttonOpen = findViewById(R.id.btnQuadratic)
        buttonOpen!!.setOnClickListener {
            val intent = Intent(".QuadraticActivity")
            startActivity(intent)
        }
    }

    private fun onClickButtonSimultaneousListener() {
        buttonSimultaneous = findViewById(R.id.btnSimultaneous)
        buttonSimultaneous!!.setOnClickListener {
            val intent = Intent(".Work")
            startActivity(intent)
        }
    }
}
