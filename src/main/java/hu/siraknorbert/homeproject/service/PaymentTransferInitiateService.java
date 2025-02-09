package hu.siraknorbert.homeproject.service;

import hu.siraknorbert.homeproject.constant.KafkaConstants;
import hu.siraknorbert.homeproject.dto.MoneyTransactionDto;
import hu.siraknorbert.homeproject.dto.PaymentTransferInitiateRequestDto;
import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;
import hu.siraknorbert.homeproject.enumeration.MoneyTransactionStatusEnum;
import hu.siraknorbert.homeproject.exception.checked.CheckedException;
import hu.siraknorbert.homeproject.exception.checked.InvalidInputException;
import hu.siraknorbert.homeproject.exception.checked.NotFoundException;
import hu.siraknorbert.homeproject.model.BankAccount;
import hu.siraknorbert.homeproject.model.MoneyTransaction;
import hu.siraknorbert.homeproject.repository.BankAccountRepository;
import hu.siraknorbert.homeproject.repository.MoneyTransactionRepository;
import hu.siraknorbert.homeproject.util.DateUtil;
import hu.siraknorbert.homeproject.util.UuidUtil;
import hu.siraknorbert.homeproject.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentTransferInitiateService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final BankAccountRepository bankAccountRepository;
    private final MoneyTransactionRepository moneyTransactionRepository;

    public MoneyTransactionDto processInitiatePaymentTransfer(PaymentTransferInitiateRequestDto request) throws CheckedException {
        ValidationUtil.isMissingChecked(request, request.getSenderId(), request.getReceiverId(), request.getAmount());
        UUID senderId = request.getSenderId();
        UUID receiverId = request.getReceiverId();
        BigDecimal amount = request.getAmount();

        if (senderId.equals(receiverId)) {
            throw new InvalidInputException("Sender and receiver bank accounts cannot be the same!", ErrorCodeEnum.MONEY_TRANSFER_SENDER_SAME_AS_RECEIVER);
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0.01) {
            throw new InvalidInputException("Amount must be at least 0.01$!", ErrorCodeEnum.MONEY_TRANSFER_SENDER_INVALID_AMOUNT);
        }
        Optional<BankAccount> optionalSenderBankAccount = bankAccountRepository.findById(senderId);
        if (optionalSenderBankAccount.isEmpty()) {
            throw new NotFoundException("Sender bank account does not exist!", ErrorCodeEnum.MONEY_TRANSFER_SENDER_NOT_FOUND);
        }
        if (optionalSenderBankAccount.get().getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidInputException("There is not enough money on the bank account!", ErrorCodeEnum.MONEY_TRANSFER_NOT_ENOUGH_MONEY);
        }
        Optional<BankAccount> optionalReceiverBankAccount = bankAccountRepository.findById(receiverId);
        if (optionalReceiverBankAccount.isEmpty()) {
            throw new NotFoundException("Receiver bank account does not exist!", ErrorCodeEnum.MONEY_TRANSFER_RECEIVER_NOT_FOUND);
        }

        MoneyTransaction moneyTransaction = createMoneyTransaction(optionalSenderBankAccount.get(), optionalReceiverBankAccount.get(), amount);
        moneyTransaction = moneyTransactionRepository.save(moneyTransaction);

        String messageKey = UuidUtil.randomUUID().toString();
        String moneyTransactionId = moneyTransaction.getId().toString();
        kafkaTemplate.send(KafkaConstants.TopicNames.PAYMENT_TRANSFER_EXECUTION_TOPIC, messageKey, moneyTransactionId);

        return convertToDto(moneyTransaction);
    }

    private MoneyTransaction createMoneyTransaction(BankAccount sender, BankAccount receiver, BigDecimal amount) {
        MoneyTransaction moneyTransaction = new MoneyTransaction();
        moneyTransaction.setId(UUID.randomUUID());
        moneyTransaction.setSenderBankAccount(sender);
        moneyTransaction.setReceiverBankAccount(receiver);
        moneyTransaction.setAmount(amount);
        moneyTransaction.setStatus(MoneyTransactionStatusEnum.PENDING);
        moneyTransaction.setInitiatedAt(DateUtil.nowUTC());
        return moneyTransaction;
    }

    private MoneyTransactionDto convertToDto(MoneyTransaction moneyTransaction) {
        MoneyTransactionDto dto = new MoneyTransactionDto();
        dto.setId(moneyTransaction.getId());
        dto.setSenderBankAccountId(moneyTransaction.getSenderBankAccount().getId());
        dto.setReceiverBankAccountId(moneyTransaction.getReceiverBankAccount().getId());
        dto.setAmount(moneyTransaction.getAmount());
        dto.setStatus(moneyTransaction.getStatus());
        dto.setInitiatedAt(moneyTransaction.getInitiatedAt());
        dto.setFinishedAt(moneyTransaction.getFinishedAt());
        return dto;
    }
}
