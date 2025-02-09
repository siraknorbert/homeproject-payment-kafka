package hu.siraknorbert.homeproject.util;

import hu.siraknorbert.homeproject.exception.checked.MissingInputException;
import hu.siraknorbert.homeproject.exception.unchecked.MissingInputUncheckedException;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.Objects;

@UtilityClass
public final class ValidationUtil {

    public static void isMissingChecked(Object... params) throws MissingInputException {
        if (isMissing(params)) {
            throw new MissingInputException();
        }
    }

    public static void isMissingUnchecked(Object... params) {
        if (isMissing(params)) {
            throw new MissingInputUncheckedException();
        }
    }

    public static boolean isMissing(Object... params) {
        return !isNotMissing(params);
    }

    public static boolean isNotMissing(Object... params) {
        for (Object param : params) {
            if (param instanceof String) {
                if (!StringUtils.hasLength((String) param)) {
                    return false;
                }
            } else {
                if (Objects.isNull(param)) {
                    return false;
                }
            }
        }
        return true;
    }
}
