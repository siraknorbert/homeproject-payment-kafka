package hu.siraknorbert.homeproject.util;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public final class DateUtil {

    public static OffsetDateTime nowUTC() {
        return OffsetDateTime.now(ZoneOffset.UTC);
    }

    public static OffsetDateTime parse(String source) {
        try {
            return OffsetDateTime.parse(source);
        } catch (Exception e) {
            return null;
        }
    }
}
