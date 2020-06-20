package com.example.mathtutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.absoluteValue
import kotlin.math.pow




class QuadraticActivity : AppCompatActivity() {
    private var buttonShowWorkings: Button? = null
    private var buttonCalculate:Button? = null
    private var a:EditText? = null
    private var b:EditText? = null
    private var c:EditText? = null
    private var textViewAnswer:TextView? = null
    private val extraMessage = "com.example.mathtutor.MESSAGE"

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quadratic)
        onClickButtonCalculateListener()
        onClickShowWorkingsButtonListener()

    }
    private fun onClickButtonCalculateListener() {
        buttonCalculate = findViewById(R.id.btnCalculate)
        buttonCalculate!!.setOnClickListener {

            quadratic()
        }

    }

    private fun onClickShowWorkingsButtonListener() {
        a = findViewById(R.id.editTextCoeffofX2)
        b = findViewById(R.id.editTextCoeffofX)
        c = findViewById(R.id.editTextCoeffofConstant)

        buttonShowWorkings = findViewById(R.id.btnShowWorking)
        buttonShowWorkings!!.setOnClickListener{
            val intent = Intent(this, Tabbed::class.java)
            val ac = Integer.parseInt(a!!.text.toString())
            val bc = Integer.parseInt(b!!.text.toString())
            val cc = Integer.parseInt(c!!.text.toString())
            val multiplyNum = ac * cc
            val x1v = factorsX1(multiplyNum,bc)
            val x2v = factorsX2(multiplyNum,bc)
            val arrayOf = intArrayOf(ac,bc,cc,x1v,x2v)
            intent.putExtra(extraMessage,arrayOf)
            startActivity(intent)
        }

                }

    private fun quadratic() {
        //variables

        a = findViewById(R.id.editTextCoeffofX2)
        b = findViewById(R.id.editTextCoeffofX)
        c = findViewById(R.id.editTextCoeffofConstant)
        textViewAnswer = findViewById(R.id.textViewAnswer)
        val ac = Integer.parseInt(a!!.text.toString())
        val bc = Integer.parseInt(b!!.text.toString())
        val cc = Integer.parseInt(c!!.text.toString())


        val determinant = (bc * bc) - (4 * ac * cc)
        if (determinant >= 0)
        {
            val denom = 2 * ac
            val x1 = (-bc + (determinant.toDouble().pow(0.5))) / (denom)
            val x2 = (-bc - determinant.toDouble().pow((0.5))) / (denom)//x1 and x2 are the roots
            val answerText = "x\u2081 = $x1 , x\u2082 = $x2"

            textViewAnswer!!.text = answerText
        }
        else
        {
            textViewAnswer!!.setText(R.string.imaginaryroots)
        }

    }

    private fun  factorsX1(multiply: Int,sum: Int): Int {

        val absSum = (multiply.absoluteValue)
        val factorsArray = arrayListOf<Int>()

        for( i in 1..absSum) {
            if (absSum % i == 0) {
                factorsArray.add(i)
                factorsArray.add(-i)

            }
        }

        val factorsArraySorted = factorsArray.sorted()
        var i = 0
        val arraySize = factorsArraySorted.size
        var r = arraySize - 1

        while (i < r) {
            val factorsArraySum = factorsArraySorted[i] + factorsArraySorted[r]
            val factorsArrayMultiply = factorsArraySorted[i] * factorsArraySorted[r]
            val x1: Int

            if (factorsArraySum == sum && factorsArrayMultiply == multiply
            ) {
                x1 = factorsArraySorted[i]
                return x1
            } else if (factorsArraySum < sum) {
                i++
            } else {
                r--
            }

        }
        return 0
    }

    private fun  factorsX2(multiply: Int,sum: Int): Int {

        val absSum = (multiply.absoluteValue)
        val factorsArray = arrayListOf<Int>()

        for( i in 1..absSum) {
            if (absSum % i == 0) {
                factorsArray.add(i)
                factorsArray.add(-i)

            }
        }

        val factorsArraySorted = factorsArray.sorted()
        var i = 0
        val arraySize = factorsArraySorted.size
        var r = arraySize - 1

        while (i < r) {
            val factorsArraySum = factorsArraySorted[i] + factorsArraySorted[r]
            val factorsArrayMultiply = factorsArraySorted[i] * factorsArraySorted[r]
            val x2: Int
            if (factorsArraySum == sum && factorsArrayMultiply == multiply
            ) {
                x2 = factorsArraySorted[r]
                return x2

            } else if (factorsArraySum < sum) {
                i++
            } else {
                r--
            }

        }
        return 0
    }

}