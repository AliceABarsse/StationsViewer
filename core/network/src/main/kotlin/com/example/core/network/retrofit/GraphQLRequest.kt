package com.example.core.network.retrofit

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String
)

