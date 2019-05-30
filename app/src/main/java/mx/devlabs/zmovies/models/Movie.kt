package mx.devlabs.zmovies.models

data class Movie(
        val id: String,
        val adult: Boolean,
        val original_title: String?,
        val poster_path : String?,
        val backdrop_path : String?,
        val overview: String?
){
    constructor(title: String) : this("", false, title, "", "", "")
}

//TODO implement Parcelable