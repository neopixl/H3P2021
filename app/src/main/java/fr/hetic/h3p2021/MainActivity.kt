package fr.hetic.h3p2021

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button.apply {
            isEnabled = false
            setOnClickListener {
                onValidate()
            }
        }


        1.run {

        }
        1.apply {

        }
        1.let {

        }
        1.also {

        }


        emailEditText.addTextChangedListener(this)
        passwordEditText.apply {
            addTextChangedListener(this@MainActivity)

            setOnEditorActionListener { view, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onValidate()
                }
                true
            }
        }

    }

    override fun afterTextChanged(s: Editable?) {
        val isFilled = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()

        button.isEnabled = isFilled
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }


    fun onValidate() {
        if (!button.isEnabled) {
            return
        }
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }

}

