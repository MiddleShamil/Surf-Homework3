package com.example.surfhomework3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val editText = findViewById<EditText>(R.id.first_name_edit_text)

        val startForResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val bundle = result.data?.extras?.getBundle(EXTRA_BUNDLE_KEY)
                val intent = Intent().apply {
                    putExtra(EXTRA_BUNDLE_KEY, bundle)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        findViewById<Button>(R.id.first_next_button).setOnClickListener {
            val firstName = editText.text.ifEmpty { "" }.toString()
            val firstData = bundleOf(
                FIRST_NAME_KEY to firstName
            )
            val newIntent = Intent(this, SecondActivity::class.java).apply {
                putExtras(firstData)
            }
            startForResultLauncher.launch(newIntent)
        }

        findViewById<Button>(R.id.first_back_button).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.first_close_button).setOnClickListener {
            finishAffinity()
        }
    }
}