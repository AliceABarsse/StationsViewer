package com.example.core.network.retrofit

import com.example.core.network.model.BrandsGraphQLResponse
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface RadioFranceGraphQLService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/v1/graphql")
    suspend fun postQuery(@Body body: JsonObject): BrandsGraphQLResponse
}