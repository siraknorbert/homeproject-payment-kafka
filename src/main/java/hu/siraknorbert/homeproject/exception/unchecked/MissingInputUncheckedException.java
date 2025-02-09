package hu.siraknorbert.homeproject.exception.unchecked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class MissingInputUncheckedException extends UncheckedException {
    private static final ErrorCodeEnum DEFAULT_ERROR_CODE = ErrorCodeEnum.INVALID_INPUT;
    private static final String DEFAULT_ERROR_MESSAGE = "A mandatory input parameter is missing!";

    public MissingInputUncheckedException() {
        super(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE);
    }

    public MissingInputUncheckedException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public MissingInputUncheckedException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public MissingInputUncheckedException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message, errorCodeEnum);
    }

    public MissingInputUncheckedException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause, errorCodeEnum);
    }

    public MissingInputUncheckedException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause, errorCodeEnum);
    }
}
