package com.example.listviewexcercise

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Bai1Activity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.bai_1)

        val editText = findViewById<EditText>(R.id.editTextNumber)
        val evenRadio = findViewById<RadioButton>(R.id.radioEven)
        val oddRadio = findViewById<RadioButton>(R.id.radioOdd)
        val squareRadio = findViewById<RadioButton>(R.id.radioSquare)
        val showBtn = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val errorText = findViewById<TextView>(R.id.textViewError)
        val toBai2Btn = findViewById<Button>(R.id.buttonToBai2)

        toBai2Btn.setOnClickListener {
            val intent = Intent(this, Bai2Activity::class.java)
            startActivity(intent)
        }

        showBtn.setOnClickListener{
            errorText.text = ""

            val inputText = editText.text.toString()

            if(TextUtils.isEmpty(inputText)) {
                errorText.text = "Please enter a positive number!"
                return@setOnClickListener
            }

            val n: Int = inputText.toIntOrNull() ?: run {
                errorText.text = "Input is not valid. Please enter a positive number!"
                return@setOnClickListener
            }

            if (n < 0) {
                errorText.text = "Input is not valid. Please enter a positive number!"
                return@setOnClickListener
            }

            val resultList = when {
                evenRadio.isChecked -> getEvenNumbers(n)
                oddRadio.isChecked -> getOddNumbers(n)
                squareRadio.isChecked -> getSquareNumbers(n)
                else -> emptyList()
            }

            if (resultList.isEmpty()) {
                errorText.text = "No number satisfied the condition!"
            } else {
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
                listView.adapter = adapter
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n) {
            if (i % 2 == 0) evenNumbers.add(i)
        }
        return evenNumbers
    }

    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 0..n) {
            if (i % 2 != 0) oddNumbers.add(i)
        }
        return oddNumbers
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}