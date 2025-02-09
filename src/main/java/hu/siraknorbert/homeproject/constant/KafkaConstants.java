package hu.siraknorbert.homeproject.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GroupIds {
        public static final String PAYMENT_TRANSFER_EXECUTION_TOPIC_GROUP_ID = "payment_transfer_execution_topic_group_id";
        public static final String PAYMENT_TRANSFER_NOTIFICATION_TOPIC_GROUP_ID = "payment_transfer_notification_topic_group_id";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TopicNames {
        public static final String EMPTY_RETRY_SUFFIX = "";
        public static final String DLT_SUFFIX = "_dlt";
        public static final String PAYMENT_TRANSFER_EXECUTION_TOPIC = "payment_transfer_execution_topic";
        public static final String PAYMENT_TRANSFER_EXECUTION_DLT_TOPIC = PAYMENT_TRANSFER_EXECUTION_TOPIC + DLT_SUFFIX;
        public static final String PAYMENT_TRANSFER_NOTIFICATION_TOPIC = "payment_transfer_notification_topic";
    }
}
