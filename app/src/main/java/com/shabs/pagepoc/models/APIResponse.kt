package com.shabs.pagepoc.models

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("Search") var search: List<Data>,
    @SerializedName("totalResults") var totalResults : String,
    @SerializedName("Response") var Response:String
)
