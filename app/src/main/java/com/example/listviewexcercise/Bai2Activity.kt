package com.example.listviewexcercise

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Bai2Activity: AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bai_2)

        val toBai3Btn = findViewById<Button>(R.id.buttonToBai3)

        toBai3Btn.setOnClickListener {
            val intent = Intent(this, Bai3Activity::class.java)
            startActivity(intent)
        }

        // Khởi tạo danh sách sinh viên
        studentList = listOf(
            Student("Nguyen Van A", "123456"),
            Student("Tran Thi B", "234567"),
            Student("Le Van C", "345678"),
            Student("Pham Thi D", "456789")
        )

        // Khởi tạo RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        // Thiết lập tìm kiếm
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    // Hàm lọc danh sách sinh viên
    private fun filter(query: String) {
        val filteredList = if (query.length > 2) {
            studentList.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
            }
        } else {
            studentList
        }
        studentAdapter.updateList(filteredList)
    }
}