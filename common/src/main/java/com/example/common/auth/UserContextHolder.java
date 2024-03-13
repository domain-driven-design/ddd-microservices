package com.example.common.auth;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = userContext.get();
        return userContext.get();
    }

    public static final void setContext(UserContext context) {
        userContext.set(context);
    }

    public static final void clear() {
        userContext.remove();
    }
}

