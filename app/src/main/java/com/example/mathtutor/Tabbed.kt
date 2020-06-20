package com.example.mathtutor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class Tabbed : AppCompatActivity() {
    private var buttonShow: Button? = null
    private var textViewStep1: TextView? = null
    private var textViewStep2:TextView? = null
    private var textViewStep3:TextView? = null
    private var textViewStep4:TextView? = null
    private var textViewStep5:TextView? = null
    private var textViewStep6:TextView? = null
    private var textViewStep7:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)
        onButtonClick()
    }
    private fun onButtonClick(){
        buttonShow = findViewById(R.id.btnShow)
        buttonShow!!.setOnClickListener {
            showWorkings()
        }
    }
    private fun  showWorkings(){
        val intent = intent
        val message = intent.getIntArrayExtra("com.example.mathtutor.MESSAGE")
        val newAc = message[0]
        val newBc = message[1]
        val newCc = message[2]
        val x1v = message[3]
        val x2v = message[4]
        val acu = checkForUnity(newAc)
        //val bcu = checkForUnity(newBc)
        //val ccu = checkForUnity(newCc)
        val bcv = isNegative(newBc)
        val ccv = isNegative(newCc)
        val x1 = isNegative(x1v)
        val x2 = isNegative(x2v)
        val factorsP = factorsParts(newAc,x1v)
        val fP = checkForUnity(factorsP)
        val factorsQ = factorsParts(newCc,x2v)
        val  factorsQIsN = isNegative(factorsQ)
        val factorsPDiv = (newAc/factorsP)
        val  fPDiv = checkForUnity(factorsPDiv)
        val x1DivAcv = x1v/factorsP
        val x1DivAc = isNegative(x1DivAcv)
        val polarizeFirst = numberPolarize(factorsQ)
        val polarizeSecond = numberPolarize(x1DivAcv)
        val x1Final = (polarizeFirst/factorsP)
        val x2Final = (polarizeSecond/factorsPDiv)

        if (x1v == 0){
            textViewStep1 = findViewById(R.id.textViewStep1)
            val step1 = "Factorization method not possible for this equation"
            textViewStep1!!.text = step1

        }
        else{
            textViewStep1 = findViewById(R.id.textViewStep1)
            val step1 = "First Step\nConverting your data back to a quadratic\n$acu x\u00B2 $bcv x $ccv = 0\n"
            textViewStep1!!.text = step1
            textViewStep2 = findViewById(R.id.textViewStep2)
            val step2 = "Second Step\nFactorizing the equation:\n$acu x² $bcv x $ccv = $acu x\u00B2 $x1 x $x2 x $ccv = 0\n"
            textViewStep2!!.text = (step2)
            textViewStep3 = findViewById(R.id.textViewStep3)
            val step3 = "Third Step\n$fP x($fPDiv x $x1DivAc ) $factorsQIsN ($fPDiv x  $x1DivAc )\n"
            textViewStep3!!.text = (step3)
            textViewStep4 = findViewById(R.id.textViewStep4)
            val step4 = "Fourth Step\n($fPDiv x $x1DivAc)($fP x $factorsQIsN) = 0\n"
            textViewStep4!!.text = (step4)
            textViewStep5 = findViewById(R.id.textViewStep5)
            val step5 = "Fifth Step\n($fPDiv x $x1DivAc ) = 0 OR ($fP x $factorsQIsN ) = 0\n"
            textViewStep5!!.text = (step5)
            textViewStep6 = findViewById(R.id.textViewStep6)
            val step6 = "Sixth Step\n$fPDiv x = $polarizeSecond OR $fP x = $polarizeFirst\n"
            textViewStep6!!.text = (step6)
            textViewStep7 = findViewById(R.id.textViewStep7)
            val step7 = "Seventh Step\n.'. x = $x2Final Or x = $x1Final (FINAL ANSWERS!!!!)\n"
            textViewStep7!!.text = (step7)

        }

    }

    //Checks if number is positive or negative.
    private fun isNegative(number: Int): String{
        return if(number < 0){
            number.toString()
        } else{
            "+ $number"
        }
    }

    //Checks if number is equal to 1.
    private fun checkForUnity(number: Int): String{
        return if (number != 1){
            number.toString()
        } else{
            ""
        }

    }

    private fun factorsParts(firstNumber: Int,secondNumber: Int): Int {
        val maxNum = max(firstNumber, secondNumber)
        val minNum = min(firstNumber, secondNumber)
        val factorsArray = arrayListOf<Int>()
        for (i in 1..maxNum) {
            if (maxNum % i == 0) {
                factorsArray.add(i)
                factorsArray.add(-i)
            }
        }
        val factorsArraySorted = factorsArray.sortedDescending()
        for (i in 0..factorsArraySorted.size) {
            if ((minNum % factorsArraySorted[i] == 0) && (maxNum % factorsArraySorted[i] == 0)) {
                return factorsArraySorted[i]
            }
        }
        return 0
    }
    private fun numberPolarize(number: Int): Int{
        return if (number < 0){
            abs(number)
        } else{
            -(number)
        }
    }

}
