package hu.siraknorbert.homeproject.dto;

import hu.siraknorbert.homeproject.constant.ValidationConstants;
import hu.siraknorbert.homeproject.enumeration.MoneyTransactionStatusEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransactionDto {

    @NotNull(message = ValidationConstants.Message.NULL)
    private UUID id;

    @NotNull(message = ValidationConstants.Message.NULL)
    private UUID senderBankAccountId;

    @NotNull(message = ValidationConstants.Message.NULL)
    private UUID receiverBankAccountId;

    @NotNull(message = ValidationConstants.Message.NULL)
    @DecimalMin(value = "0.00", message = "The amount must be at least 0.00")
    private BigDecimal amount;

    @NotNull(message = ValidationConstants.Message.NULL)
    private MoneyTransactionStatusEnum status;

    @NotNull(message = ValidationConstants.Message.NULL)
    private OffsetDateTime initiatedAt;

    private OffsetDateTime finishedAt;
}
