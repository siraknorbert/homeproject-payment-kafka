package hu.siraknorbert.homeproject.dto;

import hu.siraknorbert.homeproject.constant.ValidationConstants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransferInitiateRequestDto {

    @NotNull(message = ValidationConstants.Message.NULL)
    private UUID senderId;

    @NotNull(message = ValidationConstants.Message.NULL)
    private UUID receiverId;

    @NotNull(message = ValidationConstants.Message.NULL)
    @DecimalMin(value = "0.01", message = "The amount must be at least 0.01")
    private BigDecimal amount;
}
