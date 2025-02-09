package hu.siraknorbert.homeproject.exception.checked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class MissingInputException extends CheckedException {
    private static final ErrorCodeEnum DEFAULT_ERROR_CODE = ErrorCodeEnum.INVALID_INPUT;
    private static final String DEFAULT_ERROR_MESSAGE = "A mandatory input parameter is missing!";

    public MissingInputException() {
        super(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE);
    }

    public MissingInputException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public MissingInputException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public MissingInputException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message, errorCodeEnum);
    }

    public MissingInputException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause, errorCodeEnum);
    }

    public MissingInputException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause, errorCodeEnum);
    }
}
