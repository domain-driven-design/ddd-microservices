package datadictionarytest;


import common.DemoStatus;
import com.example.common.datadictionary.DataDictionaryType;
import com.example.common.datadictionary.Description;

public enum DataDictionaryDemo implements DataDictionaryType {
    STATUS_DEMO(DemoStatus.class, "状态");

    private final Class<? extends Description> clazz;
    private final String desc;


    DataDictionaryDemo(Class<? extends Description> clazz, String desc) {
        this.clazz = clazz;
        this.desc = desc;
    }

    @Override
    public Class<? extends Description> getClazz() {
        return this.clazz;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
