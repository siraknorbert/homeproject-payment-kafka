package hu.siraknorbert.homeproject.config;

import hu.siraknorbert.homeproject.constant.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic paymentExecutionTopic() {
        return TopicBuilder
                .name(KafkaConstants.TopicNames.PAYMENT_TRANSFER_EXECUTION_TOPIC)
                .build();
    }

    @Bean
    public NewTopic paymentNotificationTopic() {
        return TopicBuilder
                .name(KafkaConstants.TopicNames.PAYMENT_TRANSFER_NOTIFICATION_TOPIC)
                .build();
    }
}
