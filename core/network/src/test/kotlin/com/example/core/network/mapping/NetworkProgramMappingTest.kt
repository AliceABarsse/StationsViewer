package com.example.core.network.mapping

import com.example.core.network.model.NetworkShow
import org.junit.Assert.*
import org.junit.Test

class NetworkProgramMappingTest {

    @Test
    fun `toProgram mapper nominal case`() {
        // Given
        val initialNetworkShow = NetworkShow(
            id = "FRANCEINTER",
            title = "France Inter",
        )

        // When
        val result = initialNetworkShow.toProgram()

        // Then
        assertEquals(initialNetworkShow.id, result.id)
        assertEquals(initialNetworkShow.title, result.title)
    }

}