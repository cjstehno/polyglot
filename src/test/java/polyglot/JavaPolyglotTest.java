package polyglot;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JavaPolyglotTest {

    @Test
    public void java_usage() {
        PolyglotConfig conf = PolyglotConfig.configure(config -> {
            config.lang("Java");
            config.supported(true);
        });

        assertEquals(conf.getLang(), "Java");
        assertTrue(conf.getSupported());
    }
}
