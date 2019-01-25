package fr.hetic.h3p2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Old way
        validate_button.isEnabled = false
        validate_button.setOnClickListener {
            onValidate()
        }
        // New way
        validate_button.apply {
            isEnabled = false
            setOnClickListener {
                onValidate()
            }
        }

        1.apply {  }
        1.run {  }
        1.let {  }
        1.also {  }








        email_edittext.addTextChangedListener(this)
        password_edittext.addTextChangedListener(this)
        password_edittext.setOnEditorActionListener { view, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onValidate()
            }

            true
        }

    }

    fun onValidate() {
        if (!validate_button.isEnabled) {
            return
        }
        Toast.makeText(this, "CLICK!", Toast.LENGTH_SHORT).show()
    }

    override fun afterTextChanged(s: Editable?) {
        val isOk = email_edittext.text.isNotEmpty()
                && password_edittext.text.isNotEmpty()

        validate_button.isEnabled = isOk
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}
