package polyglot

import groovy.lang.Closure
import groovy.lang.DelegatesTo
import java.util.function.Consumer

class PolyglotConfig( var lang: String?, var supported: Boolean?){

    fun lang(value: String) : PolyglotConfig {
        this.lang = value
        return this
    }

    fun supported(value : Boolean): PolyglotConfig {
        this.supported = value
        return this
    }

    companion object {
        @JvmStatic
        fun configure(@DelegatesTo(PolyglotConfig::class) closure: Closure<Any>): PolyglotConfig {
            val config = PolyglotConfig("", false)
            closure.delegate = config
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call()
            return config
        }

        @JvmStatic
        fun configure(consumer: Consumer<PolyglotConfig>) : PolyglotConfig {
            val config = PolyglotConfig("", false)
            consumer.accept(config)
            return config
        }

        fun configure(config: PolyglotConfig.() -> Unit) : PolyglotConfig {
            val cfg = PolyglotConfig("", false)
            cfg.config()
            return cfg
        }
    }
}
