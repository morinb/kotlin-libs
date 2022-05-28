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
import org.apache.commons.configuration2.builder.fluent.Configurations
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler
import java.io.File
import kotlin.reflect.KProperty

/*
 * Set of utilities class and methods to handle Options
 */

/**
 * Class that represent an Int option delegate
 */
class IntOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {
    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return configs.configuration.getInt(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: Int) {
        configs.configuration.setProperty(key, value)
    }

}

/**
 * Class that represent a String option delegate
 */
class StringOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {
    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return configs.configuration.getString(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: String) {
        configs.configuration.setProperty(key, value)
    }
}

/**
 * Class that represent a Boolean option delegate
 */
class BooleanOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {
    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return configs.configuration.getBoolean(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: Boolean) {
        configs.configuration.setProperty(key, value)
    }
}

/**
 * Class that represent a Long option delegate
 */
class LongOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {
    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return configs.configuration.getLong(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: Long) {
        configs.configuration.setProperty(key, value)
    }
}

/**
 * Class that represent a Double option delegate
 */
class DoubleOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {
    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Double {
        return configs.configuration.getDouble(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: Double) {
        configs.configuration.setProperty(key, value)
    }
}

/**
 * Class that represent a Float option delegate
 */
class FloatOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {
    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Float {
        return configs.configuration.getFloat(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: Float) {
        configs.configuration.setProperty(key, value)
    }
}

/**
 * Class that represent a Short option delegate
 */
class ShortOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {
    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Short {
        return configs.configuration.getShort(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: Short) {
        configs.configuration.setProperty(key, value)
    }
}


/**
 * Class that represent a String Array option delegate
 */
class StringArrayOptionsAccessorDelegate(
    private val key: String,
    private val configs: FileBasedConfigurationBuilder<PropertiesConfiguration>
) {


    /**
     * Get the value of the option
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Array<String> {
        return configs.configuration.getStringArray(key)
    }

    /**
     * Set the value of the option
     */
    operator fun setValue(options: Any?, property: KProperty<*>, value: Array<String>) {
        configs.configuration.setProperty(key, value)
    }
}


/**
 * Get or create configuration file for a specific name.
 * This config will be passed as a parameter to the accessor delegates.
 *
 * @param name name of the configuration file
 * @return an already parameterized configuration builder
 */
fun getOrCreateConfig(name: String): FileBasedConfigurationBuilder<PropertiesConfiguration> {
    // Create a new Configurations object
    val configs = Configurations()


    // Parse the default configuration from the base-options.conf file
    val defaultConfig = configs.properties(object {}::class.java.getResource("/base-options.conf"))

    // Create a java File to store the configuration
    val confFile = File("$name.conf")

    // if confFile does not exist, create it
    val confFileDoesNotExist = !confFile.exists()
    if (confFileDoesNotExist) {
        confFile.createNewFile()
    }

    // Create a PropertiesBuilder from the configs object
    val builder = configs.propertiesBuilder(confFile)
    builder.configuration.listDelimiterHandler = DefaultListDelimiterHandler(';')

    // Gets the configuration
    val newConfig = builder.configuration

    // if confFile does not exist, copy defaultConfig into newConfig
    if (confFileDoesNotExist) {
        defaultConfig.keys.forEach { key -> newConfig.setProperty(key, defaultConfig.getProperty(key)) }
    }

    // save the builder
    builder.save()

    // return the new configuration
    return builder


}
