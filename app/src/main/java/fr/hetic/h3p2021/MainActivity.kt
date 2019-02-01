package fr.hetic.h3p2021

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GestureDetectorCompat
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








        email_edittext.addTextChangedListener(this)
        password_edittext.addTextChangedListener(this)
        password_edittext.setOnEditorActionListener { view, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onValidate()
            }

            true
        }

        email_edittext.setText("florian@neopixl.com")
        password_edittext.setText("aabbccddee")

    }

    fun onValidate() {
        if (!validate_button.isEnabled) {
            return
        }

        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("email", email_edittext.text.toString())
            putExtra("password", password_edittext.text.toString())
            putExtra("int", 0)
            putExtra("boolean", false)
        }
        startActivityForResult(intent, 8)


        /*
        val intentUrl = Intent(Intent.ACTION_SEND)
        intentUrl.type = "text/plain"
        intentUrl.putExtra(Intent.EXTRA_TEXT, "http://google.com")
        startActivity(Intent.createChooser(intentUrl, "Share URL"))

        startActivity(intentUrl)
        */
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 8) {
            val boolean = data?.extras?.getBoolean("boolean") ?: false
            val text = if (boolean) "true" else "false"

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder
                .setTitle("Retour")
                .setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", object : DialogInterface.OnClickListener {

                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }

                })
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


        super.onActivityResult(requestCode, resultCode, data)
    }
}
