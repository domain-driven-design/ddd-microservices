package common;

import com.example.common.datadictionary.Description;

import java.util.Map;

public enum DemoStatus implements Description {
    NEW("初始化"),
    UPDATED("已编辑"),
    SUBMITTED("已提交"),
    CALCULATED("已测算"),
    APPROVED("已审核"),
    REJECTED("已驳回");

    private final String description;

    DemoStatus(String description) {
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
