package hu.siraknorbert.homeproject.consumer;

import hu.siraknorbert.homeproject.constant.KafkaConstants;
import hu.siraknorbert.homeproject.exception.checked.RetryableException;
import hu.siraknorbert.homeproject.helper.ConsumerHelper;
import hu.siraknorbert.homeproject.service.PaymentTransferExecutionDltService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentTransferExecutionDltConsumer {
    private final ConsumerHelper consumerHelper;
    private final PaymentTransferExecutionDltService paymentTransferExecutionDltService;

    @KafkaListener(
            topics = KafkaConstants.TopicNames.PAYMENT_TRANSFER_EXECUTION_DLT_TOPIC,
            groupId = KafkaConstants.GroupIds.PAYMENT_TRANSFER_EXECUTION_TOPIC_GROUP_ID
    )
    void listen(final ConsumerRecord<String, String> record) throws RetryableException {
        consumerHelper.processMessage(
                paymentTransferExecutionDltService::processPaymentTransferDltExecution,
                record,
                KafkaConstants.TopicNames.PAYMENT_TRANSFER_EXECUTION_DLT_TOPIC,
                KafkaConstants.GroupIds.PAYMENT_TRANSFER_EXECUTION_TOPIC_GROUP_ID
        );
    }
}
