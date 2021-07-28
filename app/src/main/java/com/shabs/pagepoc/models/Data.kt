package com.shabs.pagepoc.models

import com.google.gson.annotations.SerializedName

class Data(
    @SerializedName("Title") var title: String,
    @SerializedName("Year") var year: String,
    @SerializedName("imdbID")var imdbID: String,
    @SerializedName("Type")var type: String,
    @SerializedName("Poster")var poster: String
)
