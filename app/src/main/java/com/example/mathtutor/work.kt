package com.example.mathtutor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mathtutor.ui.work.WorkFragment


class Work: AppCompatActivity() {

    private var buttonShowWorkings: Button? = null
    private var buttonCalculate: Button? = null
    private var a: EditText? = null
    private var b: EditText? = null
    private var textViewAnswer: TextView? = null
    //private val firstEquation = (a!!.text.toString())
    //private val secondEquation = (b!!.text.toString())

    //private val extraMessage = "com.example.mathtutor.MESSAGE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_activity)
        /* if(findViewById<View>(R.id.work_fragment) != null){

             if(savedInstanceState != null){
                 return
             }

         }
         supportFragmentManager.beginTransaction()
             .replace(R.id.simultaneous_home, WorkFragment.newInstance())
             .commitNow()
             */

        onClickButtonCalculateListener()
        onClickButtonWorkingsListener()
    }

    private fun onClickButtonCalculateListener() {

        buttonCalculate = findViewById(R.id.btnSimulCalc)
        buttonCalculate!!.setOnClickListener {

            simultaneous()

        }

    }

    private fun onClickButtonWorkingsListener() {

        buttonShowWorkings = findViewById(R.id.btnSimulWorkings)
        buttonShowWorkings!!.setOnClickListener {

            showWorkings()

        }

    }

    private fun checkFor(equation: String): List<String>? {
        val trim = equation.replace("\\s".toRegex(), "")
        val strTrim = trim.replace("-", " -")
        val aac = trim.toMutableList()
        val bc = strTrim.replace("-\\D".toRegex(), "-1")
        val cc = bc.replace("\\+[\\D]".toRegex(), "+1")

        return if (aac.contains('=') && (aac.contains('+') || aac.contains('-')) && (aac.size >= 5)) {
            //return trim.split("[^-\\d]+".toRegex())
            val strSplitTotal = cc.split("[^-.?\\d]+".toRegex())
            strSplitTotal.filter { !it.matches("-?".toRegex()) }
        } else {
            null
        }
    }

    private fun simultaneous() {

        a = findViewById(R.id.editTextFirstEquation)
        b = findViewById(R.id.editTextSecondEquation)
        val firstEquation = (a!!.text.toString())
        val secondEquation = (b!!.text.toString())
        textViewAnswer = findViewById(R.id.textViewAnswer)

        val split = checkFor(firstEquation)
        val splitSecond = checkFor(secondEquation)

        // val trimArray = listOf(trimOut.trim().split("")

        val a1Value = split!![0].toDouble()
        val b1Value = split[1].toDouble()
        val c1Value = split[2].toDouble()
        val a2Value = splitSecond!![0].toDouble()
        val b2Value = splitSecond[1].toDouble()
        val c2Value = splitSecond[2].toDouble()

        // val a3 = a1Value * a2Value
        val b3 = b1Value * a2Value
        val c3 = c1Value * a2Value
        //val a4 = a2Value * a1Value
        val b4 = b2Value * a1Value
        val c4 = c2Value * a1Value
        // val a5 = a3 - a4
        val b5 = b3 - b4
        val c5 = c3 - c4
        val y = (c5 / b5)
        val aEx = b1Value * y
        val x = (c1Value - aEx) / a1Value
        val answer = "x = $x And y =$y"
        textViewAnswer!!.text = answer

    }

    private fun showWorkings() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.simultaneous_home, WorkFragment.newInstance())
            .addToBackStack(null)
            .commit()

    }
}
