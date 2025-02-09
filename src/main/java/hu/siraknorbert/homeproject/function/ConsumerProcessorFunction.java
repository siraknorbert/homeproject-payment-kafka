package hu.siraknorbert.homeproject.function;

import hu.siraknorbert.homeproject.exception.checked.RetryableException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@FunctionalInterface
public interface ConsumerProcessorFunction<E extends RetryableException> {

    void process(final ConsumerRecord<String, String> record) throws E;
}
