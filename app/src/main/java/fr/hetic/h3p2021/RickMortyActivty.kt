package fr.hetic.h3p2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import fr.hetic.h3p2021.api.RickMortyClient
import fr.hetic.h3p2021.modelApi.Result
import fr.hetic.h3p2021.modelDb.RickMortyPerson
import io.realm.Case
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_rick_morty_activty.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RickMortyActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rick_morty_activty)

        Realm.init(this)


        val clientRickMortyClient = RickMortyClient()
        val personId = 1
        val request = clientRickMortyClient.api.getCharacterForId(personId)
        request.enqueue(object : Callback<Result> {

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    saveInDatabase(response.body()!!)
                } else {
                    Toast.makeText(this@RickMortyActivty, "HTTP CODE ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@RickMortyActivty, "ERROR", Toast.LENGTH_SHORT).show()
                displayData(personId)
            }

        })
    }

    fun displayData(personId: Int) {
        val realm = Realm.getDefaultInstance()

        val person = realm
            .where(RickMortyPerson::class.java)
            .equalTo("id", personId)
            .findFirst() ?: return


        Glide.with(this)
            .load(person.imageUrl)
            .into(imageView)


        nameTextView.text = "${person.name}"
        species.text = "${person.species}"
        gender.text = "${person.gender}"

        val displayDateFormater = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)

        dateTextView.text = displayDateFormater.format(person.createdDate)
    }

    fun saveInDatabase(person: Result) {
        val realm = Realm.getDefaultInstance()

        // 2017-11-05T11:15:26.044Z
        val simpleDateFomater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.FRANCE)
        simpleDateFomater.timeZone = TimeZone.getTimeZone("UTC")

        val date = simpleDateFomater.parse(person.created)


        val rickMortyPerson = RickMortyPerson().apply {
            id = person.id
            name = person.name
            gender = person.gender
            species = person.species
            imageUrl = person.image
            createdDate = date
        }

        realm.executeTransaction {
            it.copyToRealmOrUpdate(rickMortyPerson)
        }


        val personList = realm
            .where(RickMortyPerson::class.java)
            .contains("name", "rick", Case.INSENSITIVE)
            .findAll()


        Toast.makeText(this, "${personList.size}", Toast.LENGTH_LONG).show()


        displayData(person.id)

    }
}
