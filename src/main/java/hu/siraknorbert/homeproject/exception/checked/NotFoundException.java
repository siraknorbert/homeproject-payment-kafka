package hu.siraknorbert.homeproject.exception.checked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class NotFoundException extends CheckedException {
    private static final ErrorCodeEnum DEFAULT_ERROR_CODE = ErrorCodeEnum.NOT_FOUND;
    private static final String DEFAULT_ERROR_MESSAGE = "Not found...";

    public NotFoundException() {
        super(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE);
    }

    public NotFoundException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public NotFoundException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message, errorCodeEnum);
    }

    public NotFoundException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause, errorCodeEnum);
    }

    public NotFoundException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause, errorCodeEnum);
    }
}
