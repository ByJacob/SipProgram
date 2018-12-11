/*
 * Copyright 2017 Brian Christian
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.edu.pwr.weka.sipprogram.util

import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

/**
 * Wraps a [ResourceBundle] and provides a convenient [get] operator method.
 *
 * The class also suppresses [ClassCastExceptions][ClassCastException] and
 * [MissingResourceExceptions][MissingResourceException] should they occur when attempting to retrieve a bundle
 * or one of its values.
 *
 * @constructor
 * Constructs a new [MessageBundle] that wraps a [ResourceBundle] with the given [bundleName]
 *
 * If no bundle exists for the given [bundleName], then the get [instance method][get] and
 * [companion object method][MessageBundle.Companion.get] will always return the key they are given
 */

class MessageBundle(val bundleName: String): ResourceBundle() {

    @Suppress("JoinDeclarationAndAssignment")
    private val resourceBundle: ResourceBundle?

    init {
        resourceBundle = try {
            ResourceBundle.getBundle(bundleName, CustomResourceBundleControl("UTF-8"))
        }
        catch (except: MissingResourceException) {
            null
        }

        _bundles[bundleName] = this
    }

    override fun getKeys(): Enumeration<String> {
        resourceBundle?.let {
            return it.keys
        }
        return Collections.emptyEnumeration()
    }

    override fun handleGetObject(key: String?): Any {
        key?.let { return this[it] }
        return key.toString()
    }

    /**
     * Returns a [String] that corresponds to [key] in the resource bundle specified by [bundleName],
     * or [key] if no [ResourceBundle] with the name [bundleName] exists or there is no corresponding
     * [String] in the [ResourceBundle] that does exist
     */

    operator fun get(key: String): String {
        return try {
            if (true == resourceBundle?.containsKey(key)) resourceBundle.getString(key) else key
        }
        catch (except: ClassCastException) {
            key
        }
    }

    companion object {
        @JvmStatic
        private val _bundles = hashMapOf<String, MessageBundle>()

        /**
         * A [Map] mapping each instantiated [MessageBundle's][MessageBundle] [bundleName] to itself
         */
        @JvmStatic
        val bundles: Map<String, MessageBundle>
            get() = _bundles

        /**
         * Returns a [String] that corresponds to [key] in the resource bundle specified by [bundleName],
         * or [key] if no [ResourceBundle] with the name [bundleName] exists or there is no corresponding
         * [String] in the [ResourceBundle] that does exist
         */
        @JvmStatic
        operator fun get(bundleName: String, key: String) = bundles[bundleName]?.get(key) ?: key
    }

    private class CustomResourceBundleControl(encodingSchemeStr: String) : Control() {

        private var encodingScheme = "UTF-8"

        init {
            encodingScheme = encodingSchemeStr
        }

        @Throws(IllegalAccessException::class, InstantiationException::class, IOException::class)
        override fun newBundle(baseName: String, locale: Locale, format: String, loader: ClassLoader, reload: Boolean): ResourceBundle? {
            val bundleName = toBundleName(baseName, locale)
            val resourceName = toResourceName(bundleName, "properties")
            var bundle: ResourceBundle? = null
            var stream: InputStream? = null
            if (reload) {
                val url = loader.getResource(resourceName)
                if (url != null) {
                    val connection = url.openConnection()
                    if (connection != null) {
                        connection.useCaches = false
                        stream = connection.getInputStream()
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName)
            }
            if (stream != null) {
                try {
                    // Read properties files as user defined and if not provided then use "UTF-8" encoding.
                    bundle = PropertyResourceBundle(InputStreamReader(stream, encodingScheme))
                } finally {
                    stream.close()
                }
            }
            return bundle
        }
    }
}