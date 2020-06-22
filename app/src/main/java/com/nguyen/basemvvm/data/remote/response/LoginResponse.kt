package com.nguyen.basemvvm.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") var accessToken: String,
    @SerializedName("token_type") var tokenType: String,
    @SerializedName("refresh_token") var refreshToken: String,
    @SerializedName("scope") var scope: String)