package fr.hetic.h3p2021.rickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import fr.hetic.h3p2021.R
import fr.hetic.h3p2021.rickmorty.model.Result
import fr.hetic.h3p2021.rickmorty.model.RickMortyWrapper
import kotlinx.android.synthetic.main.activity_rick_morty.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RickMortyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rick_morty)

        val client = RickMortyClient()
        val request = client.api.getCharacter(48)

        request.enqueue(object : Callback<Result> {

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@RickMortyActivity, "ERROR", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@RickMortyActivity, "HTTP CODE ${response.code()}", Toast.LENGTH_SHORT).show()
                    return
                }


                displayData(response.body()!!)
            }

        })
    }

    fun displayData(character: Result) {

        nameTextView.text = character.name
        genderTextView.text = character.gender

        Glide.with(this)
            .load(character.image)
            .into(imageView)

        //2017-11-04T18:50:21.651Z
        val simpleDateFormater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        simpleDateFormater.timeZone = TimeZone.getTimeZone("GMT")

        val date = simpleDateFormater.parse(character.created)


        val toDisplayDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)

        dateTextView.text = toDisplayDateFormat.format(date)


    }
}
