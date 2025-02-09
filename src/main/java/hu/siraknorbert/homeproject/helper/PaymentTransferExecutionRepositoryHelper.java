package hu.siraknorbert.homeproject.helper;

import hu.siraknorbert.homeproject.dto.ExecutePaymentTransferEmailDto;
import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;
import hu.siraknorbert.homeproject.enumeration.MoneyTransactionStatusEnum;
import hu.siraknorbert.homeproject.exception.unchecked.NonRetryableUncheckedException;
import hu.siraknorbert.homeproject.model.BankAccount;
import hu.siraknorbert.homeproject.model.MoneyTransaction;
import hu.siraknorbert.homeproject.repository.BankAccountRepository;
import hu.siraknorbert.homeproject.repository.MoneyTransactionRepository;
import hu.siraknorbert.homeproject.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentTransferExecutionRepositoryHelper {
    private final MoneyTransactionRepository moneyTransactionRepository;
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public ExecutePaymentTransferEmailDto executePaymentTransfer(UUID moneyTransactionId) {
        MoneyTransaction moneyTransaction = moneyTransactionRepository.findById(moneyTransactionId)
                .orElseThrow(() -> new NonRetryableUncheckedException(MessageFormat.format("Money transaction with id [{0}] not found!", moneyTransactionId)));
        BankAccount senderAccount = moneyTransaction.getSenderBankAccount();
        if (senderAccount.getBalance().subtract(moneyTransaction.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            throw new NonRetryableUncheckedException("There is not enough money on the sender bank account!", ErrorCodeEnum.MONEY_TRANSFER_NOT_ENOUGH_MONEY);
        }
        BankAccount receiverAccount = moneyTransaction.getReceiverBankAccount();

        senderAccount.setBalance(senderAccount.getBalance().subtract(moneyTransaction.getAmount()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(moneyTransaction.getAmount()));
        moneyTransaction.setStatus(MoneyTransactionStatusEnum.SUCCEEDED);
        moneyTransaction.setFinishedAt(DateUtil.nowUTC());

        moneyTransactionRepository.save(moneyTransaction);
        bankAccountRepository.saveAll(List.of(senderAccount, receiverAccount));

        return createMailDataDto(senderAccount.getOwnerName(), receiverAccount.getOwnerEmail(), moneyTransaction.getAmount());
    }

    private ExecutePaymentTransferEmailDto createMailDataDto(String senderName, String receiverMail, BigDecimal amount) {
        ExecutePaymentTransferEmailDto dto = new ExecutePaymentTransferEmailDto();
        dto.setSenderName(senderName);
        dto.setReceiverEmail(receiverMail);
        dto.setAmount(amount);
        return dto;
    }
}
