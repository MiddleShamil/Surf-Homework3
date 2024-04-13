package com.example.surfhomework3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val FIRST_NAME_KEY = "FIRST_NAME"
const val SECOND_NAME_KEY = "SECOND_NAME"
const val AGE_KEY = "AGE"
const val EXTRA_BUNDLE_KEY = "Bundle"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resultListenerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.extras?.getBundle(EXTRA_BUNDLE_KEY)
                val firstName = data?.getString(FIRST_NAME_KEY) ?: ""
                val secondName = data?.getString(SECOND_NAME_KEY) ?: ""
                val age = data?.getString(AGE_KEY) ?: ""
                Toast.makeText(this, "Добро пожаловать, $firstName $secondName", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        findViewById<Button>(R.id.start_registration_button).setOnClickListener {
            val newIntent = Intent(this, FirstActivity::class.java)
            resultListenerLauncher.launch(newIntent)
        }

    }
}