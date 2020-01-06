package com.android.example.imageview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListers()
    }

    private fun makeImage(view: View) {
        when (view.id) {

            R.id.box_one_text -> view.setBackgroundResource(R.drawable.ic_launcher_background)
            R.id.box_two_text -> view.setBackgroundResource(R.drawable.box_one_background)
            R.id.box_three_text -> view.setBackgroundResource(R.drawable.box_two_background)
            R.id.box_four_text -> view.setBackgroundResource(R.drawable.box_one_foreground)
            R.id.box_five_text -> view.setBackgroundResource(R.drawable.box_two_background)
            else -> view.setBackgroundResource(R.drawable.ic_launcher_background)
        }
    }

    private fun setListers() {

        val boxOneImage = findViewById<TextView>(R.id.box_one_text)
        val boxTwoImage = findViewById<TextView>(R.id.box_two_text)
        val boxThreeImage = findViewById<TextView>(R.id.box_three_text)
        val boxFourImage = findViewById<TextView>(R.id.box_four_text)
        val boxFiveImage = findViewById<TextView>(R.id.box_five_text)

        val clickableTextView = listOf<TextView>(
            boxOneImage, boxTwoImage, boxThreeImage,
            boxFourImage, boxFiveImage
        )

        for (item in clickableTextView) {
            item.setOnClickListener { makeImage(it) }

        }
    }
}
