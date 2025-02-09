package hu.siraknorbert.homeproject.helper;

import hu.siraknorbert.homeproject.exception.checked.RetryableException;
import hu.siraknorbert.homeproject.function.ConsumerProcessorFunction;
import hu.siraknorbert.homeproject.util.ValidationUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class ConsumerHelper {

    public <E extends RetryableException>
    void processMessage(ConsumerProcessorFunction<E> processor,
                        final ConsumerRecord<String, String> record,
                        final String topic,
                        final String groupId
    ) throws RetryableException {
        String logMessage = "Non-retryable error occurred during consumption of message: [{0}] on topic: [{1}], groupId: [{2}].";
        try {
            ValidationUtil.isMissingUnchecked(processor, record, topic, groupId);
            processor.process(record);
        } catch (RetryableException ex) {
            logMessage = "Retryable error occurred during consumption of message: [{0}] on topic: [{1}], groupId: [{2}].";
            throw ex;
        } finally {
            // dummy logging
            System.out.println(MessageFormat.format(logMessage, record, topic, groupId));
        }
    }
}
