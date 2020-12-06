package com.mediquo.breakingbad.presentation.common

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateExtensionsKtTest {

    @Test
    fun `yearsUntilNow() returns the years passed`() {
        val calendar = Calendar.getInstance()
        calendar.set(1987, 6, 23)
        val oldDate = calendar.time // Old Date
        calendar.set(2020, 12, 5)
        val currentDate = calendar.time // Current Date

        assertEquals(33, oldDate.yearsUntilNow(currentDate))
    }
}