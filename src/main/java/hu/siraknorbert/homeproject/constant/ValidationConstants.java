package hu.siraknorbert.homeproject.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

/**
 * The utility class {@link ValidationConstants} is used to
 * group any constant values that are used for validation.
 *
 * @author Norbert Sirák
 * @see Message
 */
@UtilityClass
public class ValidationConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Message {
        public static final String BLANK = "Value must not be null or empty string!";
        public static final String NULL = "Value must not be null!";
        public static final String INVALID_SIZE = "The value must be between {min} and {max} characters long!";
    }
}
