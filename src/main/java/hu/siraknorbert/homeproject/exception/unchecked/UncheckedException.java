package hu.siraknorbert.homeproject.exception.unchecked;

import hu.siraknorbert.homeproject.enumeration.ErrorCodeEnum;

public class UncheckedException extends RuntimeException {

    private final ErrorCodeEnum errorCodeEnum;

    public UncheckedException(ErrorCodeEnum errorCodeEnum) {
        super();
        this.errorCodeEnum = errorCodeEnum;
    }

    public UncheckedException(String message, ErrorCodeEnum errorCodeEnum) {
        super(message);
        this.errorCodeEnum = errorCodeEnum;
    }

    public UncheckedException(String message, Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(message, cause);
        this.errorCodeEnum = errorCodeEnum;
    }

    public UncheckedException(Throwable cause, ErrorCodeEnum errorCodeEnum) {
        super(cause);
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}
