package fr.hetic.h3p2021.modelDb

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


open class RickMortyPerson() : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var gender: String? = null
    var species: String? = null
    var imageUrl: String? = null
    var createdDate: Date? = null

}