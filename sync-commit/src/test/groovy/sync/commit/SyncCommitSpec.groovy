package sync.commit

import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class SyncCommitSpec extends Specification {

    @Inject
    KafkaAdminActor actor

    @Inject
    Producer producer

    def "when an exception during processing occurs, no ack should be send"() {
        given:
        def initialOffset = actor.getConsumerOffset("testTopic", 0)
        def faultyMessage = "faulty"
        def message = "normal"

        when:
        producer.sendMessage(faultyMessage)
        producer.sendMessage(message)
        Thread.sleep(5000)

        then:
        def newOffset = actor.getConsumerOffset("testTopic", 0)
        initialOffset == newOffset
    }
}
