package fr.hetic.h3p2021

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import fr.hetic.h3p2021.recyclerview.PokemonActivity
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

        pokemonButton.setOnClickListener {
            goToPokemon()
        }

        rickMortyButton.setOnClickListener {
            goToRickMorty()
        }

        geolocButton.setOnClickListener {
            goToGeolocPage()
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

        emailEditText.setText("florian@neopixl.com")
        passwordEditText.setText("aabbccddee")

    }

    private fun goToGeolocPage() {
        val intent = Intent(this, GeolocActivity::class.java)
        startActivity(intent)
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

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("email", emailEditText.text.toString())
        intent.putExtra("prix", 20)
        intent.putExtra("promotion", true)
        startActivityForResult(intent, 3)
    }

    fun goToPokemon() {
        val intent = Intent(this, PokemonActivity::class.java)
        startActivity(intent)
    }

    fun goToRickMorty() {
        val intent = Intent(this, RickMortyActivty::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 3) {
            data?.extras?.apply {
                val theBooleanStr: String
                if (getBoolean("boolean")) {
                    theBooleanStr = "true"
                } else {
                    theBooleanStr = "false"
                }


                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Message d'information : " + theBooleanStr)
                    .setTitle("Retour")
                    .setPositiveButton("OK", object : DialogInterface.OnClickListener {

                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }

                    })
                    .setCancelable(false)
                val dialog = builder.create()
                dialog.show()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}

