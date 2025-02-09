package hu.siraknorbert.homeproject.exception.checked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class RetryableException extends CheckedException {
    private static final ErrorCodeEnum DEFAULT_ERROR_CODE = ErrorCodeEnum.RETRYABLE_ERROR;
    private static final String DEFAULT_ERROR_MESSAGE = "Retryable error occurred...";

    public RetryableException() {
        super(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE);
    }

    public RetryableException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public RetryableException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public RetryableException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message, errorCodeEnum);
    }

    public RetryableException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause, errorCodeEnum);
    }

    public RetryableException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause, errorCodeEnum);
    }
}
