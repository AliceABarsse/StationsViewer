package com.example.core.network.retrofit

import kotlinx.serialization.json.JsonObject
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RadioFranceGraphQLService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/v1/graphql")
    suspend fun postQuery(@Body body: JsonObject): ResponseBody
}