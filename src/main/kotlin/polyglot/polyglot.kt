/**
 * Copyright (C) 2017 Christopher J. Stehno <chris@stehno.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package polyglot

import groovy.lang.Closure
import groovy.lang.DelegatesTo
import java.util.function.Consumer

/**
 * Simple example of a DSL-style configuration interface with built-in bindings for:
 *
 * * **Kotlin** - provides a lambda-style DSL configuration.
 * * **Java** - provides a Java `Consumer<PolyglotConfig>` configuration provider.
 * * **Groovy** - provides a Groovy `Closure` DSL configuration interface.
 *
 * @property lang the language being tested
 * @property supported whether or not it is supported
 */
class PolyglotConfig(var lang: String = "", var supported: Boolean = false) {

    /**
     * DSL-style method for setting the `lang` property.
     *
     * @param value the value to be used as the `lang` property.
     */
    fun lang(value: String): PolyglotConfig {
        this.lang = value
        return this
    }

    /**
     * DSL-style method for setting the `supported` property.
     *
     * @param value the value to be used as the `supported` property.
     */
    fun supported(value: Boolean): PolyglotConfig {
        this.supported = value
        return this
    }

    companion object {
        /**
         * Used to create a `PolyglotConfig` object using a Groovy DSL-style `Closure`.
         *
         * @param closure the Groovy `Closure` used to build the configuration
         */
        @JvmStatic
        fun configure(@DelegatesTo(PolyglotConfig::class) closure: Closure<Any>): PolyglotConfig {
            val config = PolyglotConfig()
            closure.delegate = config
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call()
            return config
        }

        /**
         * Used to create a `PolyglotConfig` object using a Java `Consumer<PolyglotConfig>` instance.
         *
         * @param consumer the Java `Consumer<PolyglotConfig>` used to build the configuration
         */
        @JvmStatic
        fun configure(consumer: Consumer<PolyglotConfig>): PolyglotConfig {
            val config = PolyglotConfig()
            consumer.accept(config)
            return config
        }

        /**
         * Used to create a `PolyglotConfig` object using a Kotlin lambda function.
         *
         * @param config the Kotlin configuration lambda function.
         */
        fun configure(config: PolyglotConfig.() -> Unit): PolyglotConfig {
            val cfg = PolyglotConfig()
            cfg.config()
            return cfg
        }
    }
}
