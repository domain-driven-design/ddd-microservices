package datadictionary;

import java.util.Collections;
import java.util.Map;

public interface Description {

    String getCode();
    String getDescription();

    default Map<String, String> getExtraFields() {
        return Collections.emptyMap();
    }

}
