package com.example.listviewexcercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Bai4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.bai_4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewGroup: LinearLayout = findViewById(R.id.viewGroup)
        val imageSelected: ImageView = findViewById(R.id.imageSelected)

        val captionList: ArrayList<String> = ArrayList()
        val imageList: ArrayList<Int> = ArrayList()

        for (i in 1..11) {
            captionList.add("Data-$i")
            imageList.add(resources.getIdentifier("thumb$i", "drawable", packageName))
        }

        for (i in 0..<captionList.size) {
            val view: View = LayoutInflater.from(this).inflate(R.layout.frame_icon_caption, viewGroup, false)
            view.id = i

            view.findViewById<TextView>(R.id.caption).text = captionList[i]
            view.findViewById<ImageView>(R.id.icon).setImageResource(imageList[i])

            viewGroup.addView(view)
            view.setOnClickListener {
                imageSelected.setImageResource(imageList[view.id])
            }
        }
    }
}