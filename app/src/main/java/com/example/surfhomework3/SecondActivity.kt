package com.example.surfhomework3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val editText = findViewById<EditText>(R.id.second_name_edit_text)

        val startForResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val bundle = result.data?.extras?.getBundle(EXTRA_BUNDLE_KEY)
                val intent = Intent().apply{
                    putExtra(EXTRA_BUNDLE_KEY, bundle)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        findViewById<Button>(R.id.second_next_button).setOnClickListener {
            intent.extras?.let {
                val firstName = it.getString(FIRST_NAME_KEY) ?: ""
                val secondName = editText.text.ifEmpty { "" }.toString()
                val secondData = bundleOf(
                    FIRST_NAME_KEY to firstName,
                    SECOND_NAME_KEY to secondName
                )
                val newIntent = Intent(this, ThirdActivity::class.java).apply{
                    putExtras(secondData)
                }
                startForResultLauncher.launch(newIntent)
            }
        }

        findViewById<Button>(R.id.second_back_button).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.second_close_button).setOnClickListener {
            finishAffinity()
        }
    }
}