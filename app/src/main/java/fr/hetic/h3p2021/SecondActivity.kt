package fr.hetic.h3p2021

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        intent?.extras?.run {
            val email = getString("email")
            val currentText = textView.text.toString()

            textView.text = "$currentText $email"
        }

        yesButton.setOnClickListener {
            onYesClick()
        }


        noButton.setOnClickListener {
            onNoClick()
        }
    }

    private fun onYesClick() {
        val intent = Intent().apply {
            putExtra("boolean", true)
        }

        setResult(Activity.RESULT_OK, intent)
        this.finish()

    }

    private fun onNoClick() {
        val intent = Intent().apply {
            putExtra("boolean", false)
        }

        setResult(Activity.RESULT_CANCELED, intent)
        this.finish()

    }
}
