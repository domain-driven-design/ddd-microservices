package datadictionary;

import java.util.Map;

public interface Description {

    String getCode();
    String getDescription();
    Map<String, String> getExtraFields();

}
