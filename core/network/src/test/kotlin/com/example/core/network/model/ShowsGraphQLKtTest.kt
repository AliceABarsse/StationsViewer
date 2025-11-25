package com.example.core.network.model

import com.example.core.network.retrofit.getSuccessResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.serialization.json.JsonObject
import org.junit.Test
import java.util.UUID

class ShowsGraphQLKtTest {

    private val networkShows = List(
        size = 4,
        init = {
            NetworkShow(
                id = UUID.randomUUID().toString(),
                title = UUID.randomUUID().toString(),
            )
        },
    )

    @Test
    fun `getProgramsQuery basic valid input`() {
        // Given - When
        val result : JsonObject = getProgramsQuery(stationId = "FRANCEINTER", pageSize = 33, lastCursor = "cursor123")

        // Then
        assertEquals("""
            {"station":"FRANCEINTER","first":33,"after":"cursor123"}
            """.trimIndent(), result["variables"].toString())
    }

    @Test
    fun `getProgramsQuery null lastCursor`() {
        // Given - When
        val result : JsonObject = getProgramsQuery(stationId = "FRANCEINTER", pageSize = 10, lastCursor = null)

        // Then
        assertEquals("""
            {"station":"FRANCEINTER","first":10,"after":null}
            """.trimIndent(), result["variables"].toString())
    }

    @Test
    fun `getProgramsQuery default arguments`() {
        // Given - When
        val result : JsonObject = getProgramsQuery(stationId = "FRANCEINTER")

        // Then
        assertEquals("""
            {"station":"FRANCEINTER","first":10,"after":null}
            """.trimIndent(), result["variables"].toString())
    }

    @Test
    fun `getProgramsQuery empty stationId`() {
        // Given - When
        val result : JsonObject = getProgramsQuery(stationId = "")

        // Then
        assertEquals("""
            {"station":"","first":10,"after":null}
            """.trimIndent(), result["variables"].toString())
    }

    @Test
    fun `getItems basic happy path`() {
        // Given
        val successResponse =
            getSuccessResponse(networkShows = networkShows)

        // When
        val result = successResponse.items

        // Then
        assertEquals(networkShows.size, result.size)
    }

    @Test
    fun `getItems null data`() {
        // Given
        val nullResponse = ShowsGraphQLResponse(data = null)

        // When
        val result = nullResponse.items

        // Then
        assertEquals(true, result.isEmpty())
    }

    @Test
    fun `getItems null shows`() {
        // Verify that 'items' returns an empty list when 'data.shows' is null.
        // Given
        val successResponse = getSuccessResponse(networkShows = null)

        // When
        val result = successResponse.items

        // Then
        assertEquals(true, result.isEmpty())
    }

    @Test
    fun `getItems null edges`() {
        // Given
        val nullEdgesResponse = ShowsGraphQLResponse(data = ShowsData(shows = ShowsConnection(edges = null)))

        // When
        val result = nullEdgesResponse.items

        // Then
        assertEquals(true, result.isEmpty())
    }

    @Test
    fun `getItems empty edges list`() {
        // Given
        val nullEdgesResponse = ShowsGraphQLResponse(data = ShowsData(shows = ShowsConnection(edges = emptyList())))

        // When
        val result = nullEdgesResponse.items

        // Then
        assertEquals(true, result.isEmpty())
    }

    @Test
    fun `getLastCursor basic happy path`() {
        // Given
        val successResponse =
            getSuccessResponse(networkShows = networkShows, lastCursor = "Some last cursor")

        // When
        val result = successResponse.lastCursor

        // Then
        assertEquals( "Some last cursor", result)
    }

    @Test
    fun `getLastCursor null data`() {
        // Given
        val nullResponse = ShowsGraphQLResponse(data = null)

        // When
        val result = nullResponse.lastCursor

        // Then
        assertNull(result)
    }

    @Test
    fun `getLastCursor null shows`() {
        // Given
        val successResponse = getSuccessResponse(networkShows = null)

        // When
        val result = successResponse.lastCursor

        // Then
        assertNull(result)
    }

    @Test
    fun `getLastCursor null edges`() {
        // Given
        val nullEdgesResponse = ShowsGraphQLResponse(data = ShowsData(shows = ShowsConnection(edges = null)))

        // When
        val result = nullEdgesResponse.lastCursor

        // Then
        assertNull(result)
    }

    @Test
    fun `getLastCursor empty edges list`() {
        // Given
        val nullEdgesResponse = ShowsGraphQLResponse(data = ShowsData(shows = ShowsConnection(edges = emptyList())))

        // When
        val result = nullEdgesResponse.lastCursor

        // Then
        assertNull(result)
    }

    @Test
    fun `getLastCursor last edge has null cursor`() {
        // Given
        val successResponse =
            getSuccessResponse(networkShows = networkShows, lastCursor = null)

        // When
        val result = successResponse.lastCursor

        // Then
        assertNull(result)
    }

    @Test
    fun `getLastCursor single edge`() {
        // Given
        val successResponse =
            getSuccessResponse(networkShows = networkShows.subList(0,1), lastCursor = "Only one")

        // When
        val result = successResponse.lastCursor

        // Then
        assertEquals(1, successResponse.items.size)
        assertEquals("Only one", result)
    }

}