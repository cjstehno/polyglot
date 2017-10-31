package polyglot

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KotlinPolyglotTest {

    @Test
    fun kotlinUsage() {
        val config = PolyglotConfig.configure {
            lang = "Kotlin"
            supported(true)
        }

        assertEquals(config.lang, "Kotlin")
        assertTrue(config.supported!!)
    }
}