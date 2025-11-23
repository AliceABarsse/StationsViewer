package com.example.core.model.data

import org.junit.Assert.*
import org.junit.Test

class KnownStationsTest {
    @Test
    fun getParentStation() {
        assertEquals("FIP", KnownStations.FIP_BORDEAUX.getParentStation())
        assertNull(KnownStations.ELSASS.getParentStation())
    }

}