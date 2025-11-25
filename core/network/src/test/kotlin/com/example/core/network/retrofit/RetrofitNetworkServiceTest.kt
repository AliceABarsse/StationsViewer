package com.example.core.network.retrofit

import com.example.core.model.data.Station
import com.example.core.network.model.BrandsData
import com.example.core.network.model.BrandsGraphQLResponse
import com.example.core.network.model.GraphQLError
import com.example.core.network.model.NetworkBrand
import com.example.core.network.model.NetworkShow
import com.example.core.network.model.ShowEdge
import com.example.core.network.model.ShowsConnection
import com.example.core.network.model.ShowsData
import com.example.core.network.model.ShowsGraphQLResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class RetrofitNetworkServiceTest {

    private val mockService: RadioFranceGraphQLService = mockk()

    private val testedNetworkService = RetrofitNetworkService(mockService)

    @Test
    fun `getStations returns list of stations when API call is successful`() = runTest {
        // GIVEN - Nominal case
        val networkBrand = NetworkBrand(
            id = "FRANCEINTER",
            title = "France Inter",
            baseline = "Baseline",
            description = "Description",
            localRadios = emptyList(),
        )
        val expectedStation = Station(
            id = "FRANCEINTER",
            title = "France Inter",
            description = "Description",
            baseline = "Baseline",
            isLocal = false,
        )

        val successResponse = BrandsGraphQLResponse(
            data = BrandsData(brands = listOf(networkBrand)),
            errors = null
        )

        coEvery { mockService.postBrandsQuery(any()) } returns successResponse

        // WHEN
        val result = testedNetworkService.getStations()

        // THEN
        assertEquals(1, result.size)
        assertEquals(expectedStation.id, result[0].id)
        assertEquals(expectedStation.title, result[0].title)
    }

    @Test
    fun `getStations returns empty list when API returns empty brands list`() = runTest {
        // GIVEN - Empty result
        val emptyResponse = BrandsGraphQLResponse(
            data = BrandsData(brands = emptyList()),
            errors = null
        )

        coEvery { mockService.postBrandsQuery(any()) } returns emptyResponse

        // WHEN
        val result = testedNetworkService.getStations()

        // THEN
        assertTrue(result.isEmpty())
    }

    @Test(expected = Exception::class)
    fun `getStations throws Exception when API returns GraphQL errors`() = runTest {

        // GIVEN
        val errorResponse = BrandsGraphQLResponse(
            data = null,
            errors = listOf(GraphQLError(message = "Some error"))
        )
        coEvery { mockService.postBrandsQuery(any()) } returns errorResponse

        // WHEN
        testedNetworkService.getStations()

        // THEN - Exception("GraphQL Error: ...")
    }

    @Test(expected = Exception::class)
    fun `getStations throws some Exception (network fails or parsing problem)`() = runTest {
        // GIVEN
        coEvery { mockService.postBrandsQuery(any()) } throws Exception("No internet connection")

        // WHEN
        testedNetworkService.getStations()

        // THEN Exception
    }

    @Test
    fun `getPrograms returns list of programs when API call is successful`() = runTest {
        // GIVEN - Nominal response
        val networkShows = List(
            size = 4,
            init = {
                NetworkShow(
                    id = UUID.randomUUID().toString(),
                    title = UUID.randomUUID().toString(),
                )
            },
        )
        val successResponse =
            getSuccessResponse(networkShows = networkShows, lastCursor = "Last cursor!")

        coEvery { mockService.postShowsQuery(any()) } returns successResponse

        // WHEN
        val result = testedNetworkService.getPrograms(stationId = Random.nextInt().toString())

        // THEN
        assertEquals(networkShows.size, result.list.size)
        assertEquals("Last cursor!", result.lastCursor)
        assertEquals(10, result.pageSize)
    }
    /*
        @Test
        fun `getPrograms returns empty list when API returns empty brands list`() = runTest {
            // GIVEN - Empty result
            val emptyResponse = BrandsGraphQLResponse(
                data = BrandsData(brands = emptyList()),
                errors = null
            )

            coEvery { mockService.postQuery(any()) } returns emptyResponse

            // WHEN
            val result = testedNetworkService.getPrograms(stationId = Random.nextInt().toString())

            // THEN
            assertTrue(result.isEmpty())
        }

        @Test(expected = Exception::class)
        fun `getPrograms throws Exception when API returns GraphQL errors`() = runTest {

            // GIVEN
            val errorResponse = BrandsGraphQLResponse(
                data = null,
                errors = listOf(GraphQLError(message = "Some error"))
            )
            coEvery { mockService.postQuery(any()) } returns errorResponse

            // WHEN
            testedNetworkService.getPrograms(stationId = Random.nextInt().toString())

            // THEN - Exception("GraphQL Error: ...")
        }

        @Test(expected = Exception::class)
        fun `getPrograms throws some Exception (network fails or parsing problem)`() = runTest {
            // GIVEN
            coEvery { mockService.postQuery(any()) } throws Exception("No internet connection")

            // WHEN
            testedNetworkService.getPrograms(stationId = Random.nextInt().toString())

            // THEN Exception
        }

     */
}

internal fun getSuccessResponse(
    networkShows: List<NetworkShow>?,
    lastCursor: String? = UUID.randomUUID().toString()
): ShowsGraphQLResponse = ShowsGraphQLResponse(
    data = ShowsData(
        shows = ShowsConnection(
            edges = networkShows?.mapIndexed { index, show ->
                ShowEdge(
                    node = show,
                    cursor = if (index == networkShows.size - 1) lastCursor
                    else UUID.randomUUID().toString()
                )
            },
        )
    ),
    errors = null,
)
