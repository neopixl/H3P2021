package fr.hetic.h3p2021

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import fr.hetic.h3p2021.api.RickMortyClient
import fr.hetic.h3p2021.model.Result
import fr.hetic.h3p2021.model.RickMortyWrapper
import kotlinx.android.synthetic.main.activity_rick_morty_activty.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RickMortyActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rick_morty_activty)


        val clientRickMortyClient = RickMortyClient()
        val request = clientRickMortyClient.api.getCharacterForId(27)
        request.enqueue(object : Callback<Result> {

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    displayData(response.body()!!)
                } else {
                    Toast.makeText(this@RickMortyActivty, "HTTP CODE ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@RickMortyActivty, "ERROR", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun displayData(person: Result) {
        Glide.with(this)
            .load(person.image)
            .into(imageView)


        nameTextView.text = "${person.name}"
        species.text = "${person.species}"
        gender.text = "${person.gender}"

        val simpleDateFomater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.FRANCE)
        simpleDateFomater.timeZone = TimeZone.getTimeZone("GMT")

        val date = simpleDateFomater.parse(person.created)

        val displayDateFormater = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)


        dateTextView.text = displayDateFormater.format(date)




    }
}
