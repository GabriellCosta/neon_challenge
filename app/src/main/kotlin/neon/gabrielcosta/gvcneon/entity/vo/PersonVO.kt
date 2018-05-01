package neon.gabrielcosta.gvcneon.entity.vo

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PersonDTO(@SerializedName("results") val results: List<PersonVO>)

data class PersonVO(@SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("image") val photo: String,
    @SerializedName("id") val id: Long) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(photo)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonVO> {
        override fun createFromParcel(parcel: Parcel): PersonVO {
            return PersonVO(parcel)
        }

        override fun newArray(size: Int): Array<PersonVO?> {
            return arrayOfNulls(size)
        }
    }
}