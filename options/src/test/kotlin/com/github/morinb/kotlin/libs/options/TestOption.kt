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

import org.apache.commons.configuration2.PropertiesConfiguration
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder

object TestOption {

    /**
     * private configuration by lazy getOrCreateConfig
     */
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration> by lazy { getOrCreateConfig("test") }


    /**
     * Long property by LongOptionsAccessorDelegate with key "app.long"
     */
    var long: Long by LongOptionsAccessorDelegate("app.long", configs)

    /**
     * Int property by IntOptionsAccessorDelegate with key "app.int"
     */
    var int: Int by IntOptionsAccessorDelegate("app.int", configs)

    /**
     * String property by StringOptionsAccessorDelegate with key "app.string"
     */
    var string: String by StringOptionsAccessorDelegate("app.string", configs)

    /**
     * Float property by FloatOptionsAccessorDelegate with key "app.float"
     */
    var float: Float by FloatOptionsAccessorDelegate("app.float", configs)

    /**
     * Boolean property by BooleanOptionsAccessorDelegate with key "app.boolean"
     */
    var boolean: Boolean by BooleanOptionsAccessorDelegate("app.boolean", configs)

    /**
     * String Array property by StringArrayOptionsAccessorDelegate with key "app.string-array"
     */
    var stringArray: Array<String> by StringArrayOptionsAccessorDelegate("app.string-array", configs)

    /**
     * Double property by DoubleOptionsAccessorDelegate with key "app.double"
     */
    var double: Double by DoubleOptionsAccessorDelegate("app.double", configs)

    /**
     * Short property by ShortOptionsAccessorDelegate with key "app.short"
     */
    var short: Short by ShortOptionsAccessorDelegate("app.short", configs)
}