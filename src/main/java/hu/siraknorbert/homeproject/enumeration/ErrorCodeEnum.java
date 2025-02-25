package hu.siraknorbert.homeproject.enumeration;

public enum ErrorCodeEnum {
    INVALID_INPUT,
    MONEY_TRANSFER_SENDER_SAME_AS_RECEIVER,
    MONEY_TRANSFER_SENDER_INVALID_AMOUNT,
    MONEY_TRANSFER_NOT_ENOUGH_MONEY,
    MONEY_TRANSFER_RECEIVER_NOT_FOUND,
    MONEY_TRANSFER_SENDER_NOT_FOUND,
    NON_RETRYABLE_ERROR,
    NOT_FOUND,
    RETRYABLE_ERROR,
    UNEXPECTED_ERROR
}
