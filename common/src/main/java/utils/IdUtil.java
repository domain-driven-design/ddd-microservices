package utils;

import java.util.UUID;

public class IdUtil {
    private IdUtil() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
