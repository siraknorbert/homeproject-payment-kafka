package hu.siraknorbert.homeproject.service;

import hu.siraknorbert.homeproject.enumeration.MoneyTransactionStatusEnum;
import hu.siraknorbert.homeproject.exception.checked.RetryableException;
import hu.siraknorbert.homeproject.model.MoneyTransaction;
import hu.siraknorbert.homeproject.repository.MoneyTransactionRepository;
import hu.siraknorbert.homeproject.util.DateUtil;
import hu.siraknorbert.homeproject.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentTransferExecutionDltService {
    private final MoneyTransactionRepository moneyTransactionRepository;

    public void processPaymentTransferDltExecution(final ConsumerRecord<String, String> record) throws RetryableException {
        UUID moneyTransactionId = UuidUtil.fromString(record.value());
        MoneyTransaction moneyTransaction = moneyTransactionRepository.findById(moneyTransactionId).orElseThrow();
        moneyTransaction.setStatus(MoneyTransactionStatusEnum.FAILED);
        moneyTransaction.setFinishedAt(DateUtil.nowUTC());
        moneyTransactionRepository.save(moneyTransaction);
    }
}
