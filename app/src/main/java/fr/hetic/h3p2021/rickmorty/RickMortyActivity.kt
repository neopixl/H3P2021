package fr.hetic.h3p2021.rickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import fr.hetic.h3p2021.R
import fr.hetic.h3p2021.rickmorty.modelApi.Result
import kotlinx.android.synthetic.main.activity_rick_morty.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import io.realm.RealmResults
import fr.hetic.h3p2021.rickmorty.modelDb.RickMortyPerson
import io.realm.Case
import io.realm.Realm




class RickMortyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rick_morty)

        Realm.init(this)


        val client = RickMortyClient()
        val idToDisplay = 50
        val request = client.api.getCharacter(idToDisplay)

        request.enqueue(object : Callback<Result> {

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@RickMortyActivity, "ERROR", Toast.LENGTH_SHORT).show()
                displayDataFromId(idToDisplay)
            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@RickMortyActivity, "HTTP CODE ${response.code()}", Toast.LENGTH_SHORT).show()
                    return
                }

                saveInDatabase(response.body()!!)
            }

        })
    }

    fun displayDataFromId(characterId: Int) {

        val realm = Realm.getDefaultInstance()
        val rickMortyPerson = realm
            .where(RickMortyPerson::class.java)
            .equalTo("id", characterId)
            .findFirst() ?: return

        nameTextView.text = rickMortyPerson.name
        genderTextView.text = rickMortyPerson.gender

        Glide.with(this)
            .load(rickMortyPerson.imageUrl)
            .into(imageView)


        val toDisplayDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)

        dateTextView.text = toDisplayDateFormat.format(rickMortyPerson.creationDate)


    }

    fun saveInDatabase(character: Result) {
        //2017-12-01T12:02:21.611Z
        val simpleDateFormater = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        simpleDateFormater.timeZone = TimeZone.getTimeZone("UTC")

        val date = simpleDateFormater.parse(character.created)


        val rickMortyPerson = RickMortyPerson().apply {
            id = character.id
            name = character.name
            imageUrl = character.image
            gender = character.gender
            creationDate = date
        }

        val realm = Realm.getDefaultInstance()

        realm.executeTransaction {
            it.copyToRealmOrUpdate(rickMortyPerson)
        }


        val rickMortyPersonList = realm.where(RickMortyPerson::class.java)
            .like("name", "*Rick*", Case.INSENSITIVE)
            .findAll()

        for (rickMorty in rickMortyPersonList) {

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder
                .setTitle("Rick trouve : ${rickMorty.name}")
                .show()
        }

        displayDataFromId(character.id)
    }
}
