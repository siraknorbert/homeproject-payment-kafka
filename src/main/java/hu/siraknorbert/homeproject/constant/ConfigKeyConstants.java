package hu.siraknorbert.homeproject.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigKeyConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Parts {
        public static final String CONFIG_DELIMITER = ".";
        public static final String SPRING = "spring";
        public static final String KAFKA = "kafka";
        public static final String BOOTSTRAP_SERVERS = "bootstrap-servers";
        public static final String MAIL = "mail";
        public static final String USERNAME = "username";
        public static final String SPRING_KAFKA = SPRING + CONFIG_DELIMITER + KAFKA;
        public static final String SPRING_KAFKA_BOOTSTRAP_SERVERS = SPRING_KAFKA + CONFIG_DELIMITER + BOOTSTRAP_SERVERS;
        public static final String SPRING_MAIL = SPRING + CONFIG_DELIMITER + MAIL;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Keys {
        public static final String SPRING_KAFKA_BOOTSTRAP_SERVERS = "${" + Parts.SPRING_KAFKA_BOOTSTRAP_SERVERS + "}";
        public static final String SPRING_MAIL_USERNAME = "${" + Parts.SPRING_MAIL + Parts.CONFIG_DELIMITER + Parts.USERNAME + "}";
    }
}
