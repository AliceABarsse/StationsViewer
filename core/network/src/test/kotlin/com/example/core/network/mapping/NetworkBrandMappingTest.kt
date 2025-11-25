package com.example.core.network.mapping

import com.example.core.network.model.LocalRadio
import com.example.core.network.model.NetworkBrand
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class NetworkBrandMappingTest {

    @Test
    fun `toStation mapper for brand with all fields and no local radios`() {
        // Given
        val initialNetworkBrand = NetworkBrand(
            id = "FRANCEINTER",
            title = "France Inter",
            baseline = "Baseline",
            description = "Description",
            localRadios = null,
        )

        // When
        val result = initialNetworkBrand.toStation()

        // Then
        assertEquals(1, result.size)
        val station = result.first()
        assertEquals(initialNetworkBrand.id, station.id)
        assertEquals(initialNetworkBrand.title, station.title)
        assertEquals(initialNetworkBrand.description, station.description)
        assertEquals(initialNetworkBrand.baseline, station.baseline)
        assertEquals(false, station.isLocal)
    }

    @Test
    fun `toStation mapper for brand with all fields and some local radios`() {
        // Given
        val initialNetworkBrand = NetworkBrand(
            id = "FRANCEINTER",
            title = "France Inter",
            baseline = "Baseline",
            description = "Description",
            localRadios =
                    List(
                        size = 3,
                        init = {
                            LocalRadio(id = "id$it", title = "Local Radio $it")
                        },
                    )
        )

        // When
        val result = initialNetworkBrand.toStation()

        // Then
        assertEquals(4, result.size)
        val mainRadio = result.first()
        val localRadios = result.drop(1)
        assertEquals(initialNetworkBrand.id, mainRadio.id)
        assertEquals(initialNetworkBrand.title, mainRadio.title)
        assertEquals(initialNetworkBrand.description, mainRadio.description)
        assertEquals(initialNetworkBrand.baseline, mainRadio.baseline)
        assertEquals(false, mainRadio.isLocal)
        check(initialNetworkBrand.localRadios?.size == 3)
        localRadios.forEachIndexed { index, station ->
            assertEquals(initialNetworkBrand.localRadios[index].id, station.id)
            assertEquals(initialNetworkBrand.localRadios[index].title, station.title)
            assertEquals(initialNetworkBrand.localRadios[index].description, station.description)
            assertEquals(false, station.isLocal)
        }
    }

    @Test
    fun `toStation mapper for brand with nulled fields`() {
        // Given
        val expectedIsLocal = Random.nextBoolean()
        val initialNetworkBrand = NetworkBrand(
            id = "FRANCEINTER",
            title = "France Inter",
            baseline = null,
            description = null,
            localRadios =
                if (expectedIsLocal)
                    List(
                        size = 3,
                        init = {
                            LocalRadio(id = "id$it", title = "Local Radio $it")
                        },
                    )
                else emptyList(),
        )

        // When
        val result = initialNetworkBrand.toStation()

        // Then
        assertEquals(initialNetworkBrand.id, result.id)
        assertEquals(initialNetworkBrand.title, result.title)
        assertEquals("", result.description)
        assertEquals("", result.baseline)
        assertEquals(expectedIsLocal, result.isLocal)
    }
}