package com.example.core.network.model

import com.example.core.model.data.StationsEnum
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.JsonPrimitive

private val PROGRAMS_QUERY = """
    query GetShows(${"$"}station: StationsEnum!, ${"$"}first: Int, ${"$"}after: String) {
      shows(station: ${"$"}station, first: ${"$"}first, after: ${"$"}after) {
        edges {
          cursor
          node {
            id
            title
          }
        }
      }
    }
""".trimIndent()

internal fun getProgramsQuery(
    stationId: String,
    pageSize: Int = 10,
    lastCursor: String? = null,
): JsonObject = buildJsonObject {
    put("query", PROGRAMS_QUERY)

    val variables = buildJsonObject {
        put(
            "station",
            JsonPrimitive(StationsEnum.valueOf(stationId).name),
            )
        put("first", pageSize)
        put("after", lastCursor)
    }
    put("variables", variables)
}

@Serializable
internal data class ShowsGraphQLResponse(
    val data: ShowsData? = null,
    val errors: List<GraphQLError>? = null,
)

@Serializable
internal data class ShowsData(
    val shows: ShowsConnection? = null,
)

@Serializable
internal data class ShowsConnection(
    val edges: List<ShowEdge>? = null,
)

@Serializable
internal data class ShowEdge(
    val cursor: String? = null,
    val node: NetworkShow? = null,
)

@Serializable
data class NetworkShow(
    val id: String,
    val title: String,
)

internal val ShowsGraphQLResponse.items: List<NetworkShow>
    get() = this.data?.shows?.edges?.mapNotNull { it.node } ?: emptyList()

internal val ShowsGraphQLResponse.lastCursor: String?
    get() = this.data?.shows?.edges?.lastOrNull()?.cursor

