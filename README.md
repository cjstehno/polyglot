# Polyglot DSL

Simple project to test an idea I had about using Kotlin to develop a DSL which can be made painlessly available to Kotlin, Java and Groovy.

> Note: I am fairly new to Kotlin, so I make no promises that this is actually _good_ Kotlin code, but it _does_ work.

The goal was to provide a common code-base and configuration objects, but then allow for more customized configuration methods (DSL-style) as available to the languages.

## Kotlin

Since the base code is written in Kotlin, the Kotlin interface is pretty straight-forward and very DSL-like:

```kotlin
PolyglotConfig.configure {
    lang = "Kotlin"
    supported(true)
}
```

which is implemented as: 

```kotlin
fun configure(config: PolyglotConfig.() -> Unit): PolyglotConfig {
    val cfg = PolyglotConfig("", false)
    cfg.config()
    return cfg
}
```

## Java 

The Java configuration is provided using a `Consumer<PolyglotConfig>` which can be inlined for a more DSL-like feel.

```java
PolyglotConfig.configure(config -> {
    config.lang("Java");
    config.supported(true);
})
```

which is implemented as: 

```kotlin
@JvmStatic
fun configure(consumer: Consumer<PolyglotConfig>): PolyglotConfig {
    val config = PolyglotConfig("", false)
    consumer.accept(config)
    return config
}
```

## Groovy

The Groovy configuration is exposed as a very Groovy DSL.

```groovy
PolyglotConfig.configure {
    lang 'Groovy'
    supported true
}
```

which is implemented as: 

```kotlin
@JvmStatic
fun configure(@DelegatesTo(PolyglotConfig::class) closure: Closure<Any>): PolyglotConfig {
    val config = PolyglotConfig("", false)
    closure.delegate = config
    closure.resolveStrategy = Closure.DELEGATE_FIRST
    closure.call()
    return config
}
```

Note that this is basically the same code that would be called when doing this in Groovy itself.
