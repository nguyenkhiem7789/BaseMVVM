package com.nguyen.basemvvm.data.remote.request

import com.google.gson.annotations.SerializedName

data class ListProductRequest(
    @SerializedName("channel") val channel: String?,
    @SerializedName("visitorId") val visitorId: String?,
    @SerializedName("q") val query: String?,
    @SerializedName("terminal") val terminal: String?,
    @SerializedName("_page") var page: Int,
    @SerializedName("_limit") val limit: Int
)