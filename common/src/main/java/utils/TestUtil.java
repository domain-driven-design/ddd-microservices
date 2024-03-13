package utils;

import auth.UserContext;

public class TestUtil {

    public static final String USER_CONTEXT = "userContext";

    public static String getUserContextString() {
        UserContext userContext = new UserContext();
        userContext.setUserId("testId");
        userContext.setUserName("testName");
        return JacksonUtil.toJson(userContext);
    }
}
