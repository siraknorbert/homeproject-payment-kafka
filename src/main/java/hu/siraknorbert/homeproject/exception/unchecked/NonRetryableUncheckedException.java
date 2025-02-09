package hu.siraknorbert.homeproject.exception.unchecked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class NonRetryableUncheckedException extends UncheckedException {
    private static final ErrorCodeEnum DEFAULT_ERROR_CODE = ErrorCodeEnum.NON_RETRYABLE_ERROR;
    private static final String DEFAULT_ERROR_MESSAGE = "Non-retryable exception occurred...";

    public NonRetryableUncheckedException() {
        super(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE);
    }

    public NonRetryableUncheckedException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NonRetryableUncheckedException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public NonRetryableUncheckedException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message, errorCodeEnum);
    }

    public NonRetryableUncheckedException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause, errorCodeEnum);
    }

    public NonRetryableUncheckedException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause, errorCodeEnum);
    }
}
