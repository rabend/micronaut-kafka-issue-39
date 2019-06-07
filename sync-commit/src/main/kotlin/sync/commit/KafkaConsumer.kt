package sync.commit

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetStrategy
import io.micronaut.configuration.kafka.annotation.Topic
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.common.TopicPartition
import org.slf4j.LoggerFactory
import javax.inject.Inject

@KafkaListener(offsetStrategy = OffsetStrategy.SYNC)
class KafkaConsumer {

    companion object {
        val LOGGER = LoggerFactory.getLogger(KafkaConsumer::class.java)
    }

    @Topic("testTopic")
    fun consume(message: String, offset: Long, partition: Int) {

        LOGGER.info("received message $message on partition: $partition with offset: $offset")
        if (message == "faulty") {
            throw RuntimeException("faulty message received")
        }
    }
}
