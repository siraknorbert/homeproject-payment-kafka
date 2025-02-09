package hu.siraknorbert.homeproject.exception.checked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class InvalidInputException extends CheckedException {
    private static final ErrorCodeEnum DEFAULT_ERROR_CODE = ErrorCodeEnum.INVALID_INPUT;
    private static final String DEFAULT_ERROR_MESSAGE = "Invalid input!";

    public InvalidInputException() {
        super(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE);
    }

    public InvalidInputException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public InvalidInputException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public InvalidInputException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message, errorCodeEnum);
    }

    public InvalidInputException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause, errorCodeEnum);
    }

    public InvalidInputException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause, errorCodeEnum);
    }
}
