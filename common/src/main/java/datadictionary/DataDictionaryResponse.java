package datadictionary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDictionaryResponse {

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典描述
     */
    private String description;

    /**
     * 属性列表
     */
    private List<DictionaryAttributeResponse> attributes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DictionaryAttributeResponse {
        /**
         * 枚举名
         */
        private String code;

        /**
         * 枚举描述
         */
        private String description;

        /**
         * 额外字段信息
         */
        private Map<String, String> additionalFields;
    }

    public static DataDictionaryResponse of(DataDictionary dataDictionary) {
        return DataDictionaryResponse.builder()
                .name(dataDictionary.getName())
                .description(dataDictionary.getDescription())
                .attributes(dataDictionary.getAttributes().stream()
                        .map(a -> DictionaryAttributeResponse.builder()
                                .code(a.getCode())
                                .description(a.getDescription())
                                .additionalFields(a.getExtraFields())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
