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
    localRadios {
      id
      title
      description
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


/**
 *  Example (full) data:
 *   {
 *         "id": "FRANCEINTER",
 *         "title": "France Inter",
 *         "baseline": "Le direct de France Inter",
 *         "description": "Joyeuse, savante et populaire, France Inter est la radio généraliste de service public ",
 *         "websiteUrl": "https://radiofrance.fr",
 *         "playerUrl": "https://embed.radiofrance.fr/franceinter/player?id_station=1",
 *         "liveStream": "https://icecast.radiofrance.fr/franceinter-midfi.mp3?id=openapi",
 *         "localRadios": null,
 *         "webRadios": [
 *           {
 *             "id": "FRANCEINTER_LA_MUSIQUE_INTER",
 *             "title": "La musique d'Inter",
 *             "description": "Des standards éternels aux découvertes de la playlist, retrouvez 24h/24 les pépites musicales des programmateurs de France Inter",
 *             "liveStream": "https://icecast.radiofrance.fr/franceinterlamusiqueinter-midfi.mp3?id=openapi",
 *             "playerUrl": "https://embed.radiofrance.fr/franceinter/player?id_station=1101"
 *           }
 *         ]
 *       },
 */


@Serializable
data class NetworkBrand(
    val id: String,
    val title: String,
    val baseline: String? = null,
    val description: String? = null,
    val localRadios: List<LocalRadio>? = null,
)

@Serializable
data class LocalRadio(
    val id: String,
    val title: String,
    val description: String? = null,
)

