package hu.siraknorbert.homeproject.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UuidUtil {

    public static UUID randomUUID() {
        return UUID.randomUUID();
    }

    public static UUID fromString(String source) {
        try {
            return UUID.fromString(source);
        } catch (Exception e) {
            return null;
        }
    }
}
