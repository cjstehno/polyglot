package polyglot

import spock.lang.Specification

class PolyglotSpec extends Specification {

    def 'configuration closure'(){
        when:
        def conf = PolyglotConfig.configure {
            lang 'Groovy'
            supported true
        }

        then:
        conf.lang == 'Groovy'
        conf.supported
    }
}
