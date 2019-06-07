package sync.commit

import io.micronaut.context.annotation.Value
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.common.TopicPartition

import javax.inject.Inject

class KafkaAdminActor {

    private String groupId
    private String bootstrapServers

    @Inject
    KafkaAdminActor(@Value('${kafka.consumers.default.group-id}') String groupId,
                    @Value('${kafka.bootstrap.servers}') String bootstrapServers) {
        this.groupId = groupId
        this.bootstrapServers = bootstrapServers
    }

    long getConsumerOffset(final String topicName, final int partition) {
        final AdminClient client = AdminClient.create(["bootstrap.servers": bootstrapServers])
        try {
            return client.listConsumerGroupOffsets(groupId)
                    .partitionsToOffsetAndMetadata().get()
                    .get(new TopicPartition(topicName, partition))?.offset() ?: 0
        } finally {
            client.close()
        }
    }
}
