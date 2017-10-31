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
class PolyglotConfig(var lang: String?, var supported: Boolean?) {

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
            val config = PolyglotConfig("", false)
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
            val config = PolyglotConfig("", false)
            consumer.accept(config)
            return config
        }

        /**
         * Used to create a `PolyglotConfig` object using a Kotlin lambda function.
         *
         * @param config the Kotlin configuration lambda function.
         */
        fun configure(config: PolyglotConfig.() -> Unit): PolyglotConfig {
            val cfg = PolyglotConfig("", false)
            cfg.config()
            return cfg
        }
    }
}
