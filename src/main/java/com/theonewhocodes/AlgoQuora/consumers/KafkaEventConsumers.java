package com.theonewhocodes.AlgoQuora.consumers;

import com.theonewhocodes.AlgoQuora.config.KafkaConfig;
import com.theonewhocodes.AlgoQuora.events.ViewCountEvent;
import com.theonewhocodes.AlgoQuora.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumers {

    private final Logger logger = LoggerFactory.getLogger(KafkaEventConsumers.class);

    private final QuestionRepository questionRepository;

    @KafkaListener(
            topics = KafkaConfig.TOPIC_NAME,
            groupId = "view-count-consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleViewCountEvent(ViewCountEvent viewCountEvent) {
        questionRepository.findById(viewCountEvent.getTargetId())
                .flatMap(question -> {
                    Integer views = question.getViews();
                    question.setViews(views == null ? 1 : views + 1);
                    return questionRepository.save(question);
                })
                .subscribe(
                        updatedQuestion -> logger.info("Updated view count for question {}: {}", updatedQuestion.getId(), updatedQuestion.getViews()),
                        error -> logger.error("Error updating view count for question {}", error.getMessage())
                );
    }

}
