package neon.gabrielcosta.gvcneon.entity.vo

import com.google.gson.annotations.SerializedName

class PersonDTO(@SerializedName("results") val results: List<PersonVO>)

data class PersonVO(@SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image") val photo: String)