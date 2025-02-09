package hu.siraknorbert.homeproject.exception.checked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class CheckedException extends Exception {

    private final ErrorCodeEnum errorCodeEnum;

    public CheckedException(ErrorCodeEnum errorCodeEnum) {
        super();
        this.errorCodeEnum = errorCodeEnum;
    }

    public CheckedException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message);
        this.errorCodeEnum = errorCodeEnum;
    }

    public CheckedException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause);
        this.errorCodeEnum = errorCodeEnum;
    }

    public CheckedException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause);
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}
