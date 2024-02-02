package datadictionarytest;

import datadictionary.Description;

import java.util.Map;

public enum StatusDemo implements Description {
    SUBMITTED("已提交"),
    CALCULATED("已测算");

    private final String description;

    StatusDemo(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Map<String, String> getExtraFields() {
        return null;
    }
}
