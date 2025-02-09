package hu.siraknorbert.homeproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.siraknorbert.homeproject.constant.KafkaConstants;
import hu.siraknorbert.homeproject.dto.ExecutePaymentTransferEmailDto;
import hu.siraknorbert.homeproject.exception.checked.RetryableException;
import hu.siraknorbert.homeproject.exception.unchecked.NonRetryableUncheckedException;
import hu.siraknorbert.homeproject.helper.PaymentTransferExecutionRepositoryHelper;
import hu.siraknorbert.homeproject.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentTransferExecutionService {
    private final PaymentTransferExecutionRepositoryHelper paymentTransferExecutionRepositoryHelper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void processPaymentTransferExecution(final ConsumerRecord<String, String> record) throws RetryableException {
        try {
            UUID moneyTransactionId = UuidUtil.fromString(record.value());
            ExecutePaymentTransferEmailDto mailData = paymentTransferExecutionRepositoryHelper.executePaymentTransfer(moneyTransactionId);
            String messageKey = UuidUtil.randomUUID().toString();
            String message = objectMapper.writeValueAsString(mailData);
            kafkaTemplate.send(KafkaConstants.TopicNames.PAYMENT_TRANSFER_NOTIFICATION_TOPIC, messageKey, message);
        } catch (NonRetryableUncheckedException e) {
            throw e;
        } catch (Exception e) {
            throw new RetryableException(e.getMessage());
        }
    }
}
