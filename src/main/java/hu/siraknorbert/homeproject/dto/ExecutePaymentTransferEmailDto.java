package hu.siraknorbert.homeproject.dto;

import hu.siraknorbert.homeproject.constant.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecutePaymentTransferEmailDto {

    @NotBlank(message = ValidationConstants.Message.BLANK)
    @Size(min = 1, max = 100, message = ValidationConstants.Message.INVALID_SIZE)
    private String senderName;

    @NotBlank(message = ValidationConstants.Message.BLANK)
    @Size(min = 1, max = 100, message = ValidationConstants.Message.INVALID_SIZE)
    private String receiverEmail;

    @NotNull(message = ValidationConstants.Message.NULL)
    private BigDecimal amount;
}
