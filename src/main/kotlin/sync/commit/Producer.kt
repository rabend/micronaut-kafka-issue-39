package sync.commit

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface Producer {

    @Topic("testTopic")
    fun sendMessage(message: String)
}
