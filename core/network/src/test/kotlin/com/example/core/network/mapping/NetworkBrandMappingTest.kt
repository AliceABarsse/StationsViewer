package com.example.core.network.mapping

import com.example.core.network.model.LocalRadio
import com.example.core.network.model.NetworkBrand
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class NetworkBrandMappingTest {

    @Test
    fun `toStation mapper for brand with all fields`() {
        // Given
        val expectedIsLocal = Random.nextBoolean()
        val initialNetworkBrand = NetworkBrand(
            id = "FRANCEINTER",
            title = "France Inter",
            baseline = "Baseline",
            description = "Description",
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
        assertEquals(initialNetworkBrand.description, result.description)
        assertEquals(initialNetworkBrand.baseline, result.baseline)
        assertEquals(expectedIsLocal, result.isLocal)
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