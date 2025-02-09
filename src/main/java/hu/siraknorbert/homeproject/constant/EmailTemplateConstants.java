package hu.siraknorbert.homeproject.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailTemplateConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Subject {
        public static final String MONEY_TRANSFER = "Money received";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Text {
        public static final String MONEY_TRANSFER = "Dear Customer,\n\n" +
                "You have received {0} USD from {1}.\n\n" +
                "Best regards,\n\n" +
                "Placeholder Ltd.";
    }
}
