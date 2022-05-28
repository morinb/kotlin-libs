/*
 * Copyright (c) 2022. baptiste
 *
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.morinb.kotlin.libs.options

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

internal class OptionsAccessorDelegateTest {

    companion object {
        @AfterAll
        @JvmStatic
        fun tearDown() {
            val delete = File("test.conf").delete()
            println("test.conf file deleted: $delete")
        }
    }

    /**
     * Test the TestOption long field
     */
    @Test
    @DisplayName("Test the TestOption long field")
    fun testLong() {
        val options = TestOption

        assertEquals(123456789132456789L, options.long)
        options.long = 741L
        assertEquals(741L, options.long)
    }

    /**
     * Test the TestOption int field
     */
    @Test
    @DisplayName("Test the TestOption int field")
    fun testInt() {
        val options = TestOption

        assertEquals(123456, options.int)
        options.int = 741
        assertEquals(741, options.int)
    }

    /**
     * Test the TestOption double field
     */
    @Test
    @DisplayName("Test the TestOption double field")
    fun testDouble() {
        val options = TestOption

        assertEquals(1.3e-3, options.double)
        options.double = 741.0
        assertEquals(741.0, options.double)
    }

    /**
     * Test the TestOption string field
     */
    @Test
    @DisplayName("Test the TestOption string field")
    fun testString() {
        val options = TestOption

        assertEquals("Hello", options.string)
        options.string = "world"
        assertEquals("world", options.string)
    }

    /**
     * Test the TestOption boolean field
     */
    @Test
    @DisplayName("Test the TestOption boolean field")
    fun testBoolean() {
        val options = TestOption

        assertEquals(true, options.boolean)
        options.boolean = false
        assertEquals(false, options.boolean)
    }

    /**
     * Test the TestOption String Array field
     */
    @Test
    @DisplayName("Test the TestOption String Array field")
    fun testStringArray() {
        val options = TestOption

        assertArrayEquals(arrayOf("aze", "rty", "uio"), options.stringArray)
        options.stringArray = arrayOf("Hello", "world", "!")
        assertArrayEquals(arrayOf("Hello", "world", "!"), options.stringArray)
    }

    /**
     * Test the TestOption float field
     */
    @Test
    @DisplayName("Test the TestOption float field")
    fun testFloat() {
        val options = TestOption

        assertEquals(123.456f, options.float)
        options.float = 741.0f
        assertEquals(741.0f, options.float)
    }


    /**
     * Test the TestOption short field
     */
    @Test
    @DisplayName("Test the TestOption short field")
    fun testShort() {
        val options = TestOption

        assertEquals(2.toShort(), options.short)
        options.short = 7.toShort()
        assertEquals(7.toShort(), options.short)
    }

}