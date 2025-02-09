package hu.siraknorbert.homeproject.dto;

import hu.siraknorbert.homeproject.constant.ValidationConstants;
import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    @NotNull(message = ValidationConstants.Message.NULL)
    private ErrorCodeEnum errorCode;

    @NotNull(message = ValidationConstants.Message.NULL)
    @Size(min = 1, max = 1024, message = ValidationConstants.Message.INVALID_SIZE)
    private String errorMessage;
}
