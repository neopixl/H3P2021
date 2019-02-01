package fr.hetic.h3p2021

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    var text: String = ""
    var price = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        intent?.extras?.apply {
            text = getString("email")
            price = getInt("prix")

            val previousText = helloWorldTextView.text.toString()
            helloWorldTextView.text = previousText + " " + text
        }

        noButton.setOnClickListener {
            onNoClicked()
        }

        yesButton.setOnClickListener {
            onYesClicked()
        }
    }

    private fun onYesClicked() {
        val intent = Intent()
        intent.putExtra("boolean", true)
        setResult( Activity.RESULT_OK , intent)
        this.finish()
    }

    private fun onNoClicked() {
        val intent = Intent()
        intent.putExtra("boolean", false)
        setResult( Activity.RESULT_CANCELED , intent)
        this.finish()
    }
}
