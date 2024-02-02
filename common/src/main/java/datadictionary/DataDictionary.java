package datadictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDictionary {
    private String name;
    private String description;
    private List<DictionaryAttribute> attributes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DictionaryAttribute {
        private String code;
        private String description;
        private Map<String, String> extraFields;
    }

}
