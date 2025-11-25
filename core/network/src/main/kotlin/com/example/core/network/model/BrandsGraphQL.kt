package com.example.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

private val BRANDS_QUERY = """
{
  brands {
    id
    title
    baseline
    description
    websiteUrl
    playerUrl
    liveStream
    localRadios {
      id
      title
      description
      liveStream
      playerUrl
    }
    webRadios {
      id
      title
      description
      liveStream
      playerUrl
    }
  }
}
""".trimIndent()

internal fun getBrandsQuery(): JsonObject = buildJsonObject {
    put("query", BRANDS_QUERY)
}


@Serializable
internal data class BrandsGraphQLResponse(
    val data: BrandsData? = null,
    val errors: List<GraphQLError>? = null
)

@Serializable
internal data class BrandsData(
    val brands: List<NetworkBrand>
)

@Serializable
internal data class GraphQLError(
    val message: String
)

