package hu.siraknorbert.homeproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.siraknorbert.homeproject.constant.EmailTemplateConstants;
import hu.siraknorbert.homeproject.dto.ExecutePaymentTransferEmailDto;
import hu.siraknorbert.homeproject.exception.checked.RetryableException;
import hu.siraknorbert.homeproject.exception.unchecked.NonRetryableUncheckedException;
import hu.siraknorbert.homeproject.helper.EmailSenderHelper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class PaymentTransferEmailSenderService {
    private final ObjectMapper objectMapper;
    private final EmailSenderHelper emailSenderHelper;

    public void sendEmail(final ConsumerRecord<String, String> record) throws RetryableException {
        ExecutePaymentTransferEmailDto dto = extractMailData(record.value());
        String text = MessageFormat.format(EmailTemplateConstants.Text.MONEY_TRANSFER, dto.getAmount(), dto.getSenderName());
        sendEmail(dto.getReceiverEmail(), EmailTemplateConstants.Subject.MONEY_TRANSFER, text);
    }

    private ExecutePaymentTransferEmailDto extractMailData(final String mailData) {
        try {
            return objectMapper.readValue(mailData, ExecutePaymentTransferEmailDto.class);
        } catch (Exception e) {
            throw new NonRetryableUncheckedException("Error occurred during extracting mail data from json string: " + e.getMessage());
        }
    }

    private void sendEmail(String toEmail, String subject, String text) throws RetryableException {
        try {
            emailSenderHelper.sendEmail(toEmail, subject, text);
        } catch (Exception e) {
            throw new RetryableException("Error occurred during email sending: " + e.getMessage());
        }
    }
}
