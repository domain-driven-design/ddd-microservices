package error;

public enum ErrorCodePrefix {

    CALCULATION("CAL"),
    AUTHENTICATION("AUT"),
    COMMON("COMM");

    private final String prefix;

    ErrorCodePrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return prefix;
    }

}
