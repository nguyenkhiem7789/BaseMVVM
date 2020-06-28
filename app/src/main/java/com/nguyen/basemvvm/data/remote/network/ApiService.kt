package com.nguyen.basemvvm.data.remote.network

import com.nguyen.basemvvm.data.remote.response.ListProductResponse
import com.nguyen.basemvvm.data.remote.response.LoginResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("oauth2/token/")
    fun login(@Field("username") username: String,
              @Field("password") password: String,
              @Field("client_id") clientId: String,
              @Field("client_secret") clientSecret: String,
              @Field("grant_type") grantType: String): Observable<LoginResponse>

    @GET("search/")
    fun getListProductAsync(
        @Query("channel") channel: String?,
        @Query("visitorId") visitorId: String?,
        @Query("q") query: String?,
        @Query("terminal") terminal: String?,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): Observable<ListProductResponse>

}