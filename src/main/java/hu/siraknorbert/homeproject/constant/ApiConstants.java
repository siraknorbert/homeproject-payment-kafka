package hu.siraknorbert.homeproject.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

/**
 * The utility class {@link ApiConstants} is used to group
 * any constant values that describe the API of the application.
 *
 * @author Norbert Sirák
 * @see Path
 * @see Documentation
 */
@UtilityClass
public class ApiConstants {

    /**
     * The inner class {@link Path} of {@link ApiConstants} is used to
     * group any constant values that describe URLs and their individual parts.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Path {
        public static final String API = "/api";
        public static final String PAYMENT = "/payment";
        public static final String TRANSFER = "/transfer";
        public static final String API_PAYMENT = API + PAYMENT;
    }

    /**
     * The inner class {@link Documentation} of {@link ApiConstants} is used to group
     * any constant values that describe the API.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Documentation {
        // REST
        public static final String PAYMENT_REST_NAME = "Payment REST API";
        public static final String PAYMENT_REST_DESCRIPTION = "Payment REST API functionalities.";

        // description parts
        public static final String DESCRIPTION_HEADERS = "| Step description | Status | Error code |\n|----|----|----|\n";
        public static final String UNEXPECTED_ERROR_DESCRIPTION = "| If any unexpected exception occurs during the process. | 500 | UNEXPECTED_ERROR |\n";
        public static final String INVALID_INPUT_DESCRIPTION = "| If any necessary request input is missing or has invalid format. | 400 | INVALID_INPUT |\n";
        public static final String MONEY_TRANSFER_SENDER_NOT_FOUND_DESCRIPTION = "| If any necessary request input is missing or has invalid format. | 400 | INVALID_INPUT |\n";
        public static final String MONEY_TRANSFER_SENDER_SAME_AS_RECEIVER_DESCRIPTION = "| If the sender id is identical to receiver id. | 400 | MONEY_TRANSFER_SENDER_SAME_AS_RECEIVER |\n";
        public static final String MONEY_TRANSFER_SENDER_INVALID_AMOUNT_DESCRIPTION = "| If the amount is less than 0.01. | 400 | MONEY_TRANSFER_SENDER_INVALID_AMOUNT |\n";
        public static final String MONEY_TRANSFER_RECEIVER_NOT_FOUND_DESCRIPTION = "| If the receiver bank account is not found. | 404 | MONEY_TRANSFER_RECEIVER_NOT_FOUND |\n";
        public static final String MONEY_TRANSFER_NOT_ENOUGH_MONEY_DESCRIPTION = "| If the sender bank account does not have enough money for the transfer. | 400 | MONEY_TRANSFER_NOT_ENOUGH_MONEY |\n";
        public static final String MONEY_TRANSFER_GENERIC_DESCRIPTION = "| If no error occurs, a PENDING money transaction is created and returned. " +
                "It will be handled asynchronously afterwards until it's status becomes SUCCEEDED or FAILED. | 200 | - |\n";

        // descriptions
        public static final String PAYMENT_TRANSFER_SUMMARY = "Transfer money from a bank account to another bank account.";
        public static final String GENERIC_AUTHENTICATED_ENDPOINT_DESCRIPTION = DESCRIPTION_HEADERS
                + UNEXPECTED_ERROR_DESCRIPTION
                + INVALID_INPUT_DESCRIPTION
                + MONEY_TRANSFER_SENDER_NOT_FOUND_DESCRIPTION
                + MONEY_TRANSFER_SENDER_SAME_AS_RECEIVER_DESCRIPTION
                + MONEY_TRANSFER_SENDER_INVALID_AMOUNT_DESCRIPTION
                + MONEY_TRANSFER_RECEIVER_NOT_FOUND_DESCRIPTION
                + MONEY_TRANSFER_NOT_ENOUGH_MONEY_DESCRIPTION
                + MONEY_TRANSFER_GENERIC_DESCRIPTION;
    }
}
