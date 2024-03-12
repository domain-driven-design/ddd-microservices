package error;

public enum ErrorGroup {

    CALCULATION("CAL"),
    AUTHENTICATION("AUT"),
    COMMON("COMM");

    private final String prefix;

    ErrorGroup(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return prefix;
    }

}
