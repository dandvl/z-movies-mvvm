package mx.devlabs.zmovies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        val id: String,
        val adult: Boolean,
        val original_title: String?,
        val poster_path : String?,
        val backdrop_path : String?,
        val overview: String?
) : Parcelable {
    constructor(title: String) : this("", false, title, "", "", "")
}
