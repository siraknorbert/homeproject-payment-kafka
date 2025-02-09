package hu.siraknorbert.homeproject.config;

import hu.siraknorbert.homeproject.constant.KafkaConstants;
import hu.siraknorbert.homeproject.exception.checked.RetryableException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;

import java.util.List;

@Configuration
@AllArgsConstructor
public class KafkaRetryConfig {

    @Bean
    public RetryTopicConfiguration retryTopicConfig(KafkaTemplate<String, String> kafkaTemplate) {
        return RetryTopicConfigurationBuilder
                .newInstance()
                .fixedBackOff(3000)
                .maxAttempts(3)
                .retryOn(RetryableException.class)
                .retryTopicSuffix(KafkaConstants.TopicNames.EMPTY_RETRY_SUFFIX)
                .dltSuffix(KafkaConstants.TopicNames.DLT_SUFFIX)
                .includeTopics(List.of(
                        KafkaConstants.TopicNames.PAYMENT_TRANSFER_EXECUTION_TOPIC,
                        KafkaConstants.TopicNames.PAYMENT_TRANSFER_NOTIFICATION_TOPIC))
                .create(kafkaTemplate);
    }
}
