package common;

import datadictionary.Description;

import java.util.Map;

public enum DemoEvent implements Description {
    SUBMIT("提交"),
    CALCULATE("测算"),
    REJECT("驳回"),
    APPROVE("审批");

    private final String description;

    DemoEvent(String description) {
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
