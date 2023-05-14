package ru.viknist.aston_dz_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val firstTaskButton by bind<Button>(R.id.firstTaskButton, this)
    private val secondTaskButton by bind<Button>(R.id.secondTaskButton, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstTaskButton.setOnClickListener {
            val intent = Intent(this, FirstTaskActivity::class.java)
            startActivity(intent)
        }

        secondTaskButton.setOnClickListener {
            val intent = Intent(this, SecondTaskActivity::class.java)
            startActivity(intent)
        }
    }
}