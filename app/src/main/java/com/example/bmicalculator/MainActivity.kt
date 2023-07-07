package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalculate.setOnClickListener{
            try {
                val weight = etWeight.text.toString().toDouble()
                val height = etHeight.text.toString().toDouble()/100
                var bmi = weight / (height * height)
                bmi = String.format("%.2f",bmi).toDouble()
                displayResultsOnScreen(bmi)

            }
            catch (e: Exception){
               Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun displayResultsOnScreen(bmi:Double){
        tvIndex.setText(bmi.toString())
        tvInfo.setText("Normal Range: 18.5 - 24.9")

        var resultText = ""
        var color=0

        when{
            bmi < 18.5 ->{
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.9 ->{
                resultText = "Normal"
                color = R.color.normal
            }
            bmi in 25.00..29.9 ->{
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.9 ->{
                resultText = "Obese"
                color=R.color.obese
            }
        }
        Log.i("ResultColor",color.toString())
        tvResult.setTextColor(ContextCompat.getColor(this,color))
        tvResult.setText(resultText)
    }
}