package com.ijikod.lawyer_app

import org.mockito.Mockito

class TestUtils {
        inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}