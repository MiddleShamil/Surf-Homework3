package com.example.surfhomework3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val editText = findViewById<EditText>(R.id.age_edit_text)

        findViewById<Button>(R.id.third_next_button).setOnClickListener {
            intent.extras?.let {
                val firstName = it.getString(FIRST_NAME_KEY) ?: ""
                val secondName = it.getString(SECOND_NAME_KEY) ?: ""
                val age = editText.text.ifEmpty { "" }.toString()

                val bundle = bundleOf(
                    FIRST_NAME_KEY to firstName,
                    SECOND_NAME_KEY to secondName,
                    AGE_KEY to age
                )
                val newIntent = Intent().apply {
                    putExtra(EXTRA_BUNDLE_KEY, bundle)
                }
                setResult(RESULT_OK, newIntent)
                finish()
            }
        }

        findViewById<Button>(R.id.third_back_button).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.third_close_button).setOnClickListener {
            finishAffinity()
        }

    }
}