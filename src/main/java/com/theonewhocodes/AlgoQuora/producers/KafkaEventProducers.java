package com.theonewhocodes.AlgoQuora.producers;

import com.theonewhocodes.AlgoQuora.config.KafkaConfig;
import com.theonewhocodes.AlgoQuora.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventProducers {

    private final Logger logger = LoggerFactory.getLogger(KafkaEventProducers.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent) {
        // topic, key, value
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
                .whenComplete((res, err) -> {
                    if (err != null) {
                        logger.error("Error publishing view count event: {}", err.getMessage());
                    } else {
                        logger.info("Event sent to Kafka topic {} partition {} offset {}", res.getRecordMetadata().topic(), res.getRecordMetadata().partition(), res.getRecordMetadata().offset());
                    }
                });
    }
}
